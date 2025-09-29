package com.hurryhand.backend.services;

import com.hurryhand.backend.config.MinioProperties;
import com.hurryhand.backend.exceptions.storage.FileUploadException;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.SetBucketPolicyArgs;
import io.minio.errors.ErrorResponseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class ServicePostPhotoStorageService {

    private final MinioClient minioClient;
    private final MinioProperties properties;
    private final AtomicBoolean bucketReady = new AtomicBoolean(false);

    public String uploadPhoto(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new FileUploadException("El archivo de imagen no puede estar vacio.");
        }

        ensureBucketExists();

        String objectName = buildObjectName(file.getOriginalFilename());

        try {
            PutObjectArgs.Builder builder = PutObjectArgs.builder()
                    .bucket(properties.getBucket())
                    .object(objectName)
                    .stream(file.getInputStream(), file.getSize(), -1);

            if (StringUtils.hasText(file.getContentType())) {
                builder.contentType(file.getContentType());
            }

            minioClient.putObject(builder.build());
        } catch (IOException ioException) {
            Thread.currentThread().interrupt();
            throw new FileUploadException("Se interrumpio la lectura del archivo.", ioException);
        } catch (Exception exception) {
            throw new FileUploadException("No se pudo subir la imagen a MinIO.", exception);
        }

        return buildPublicUrl(objectName);
    }

    private void ensureBucketExists() {
        if (bucketReady.get()) {
            return;
        }

        synchronized (bucketReady) {
            if (bucketReady.get()) {
                return;
            }

            try {
                boolean exists = minioClient.bucketExists(
                        BucketExistsArgs.builder().bucket(properties.getBucket()).build()
                );

                if (!exists) {
                    minioClient.makeBucket(MakeBucketArgs.builder().bucket(properties.getBucket()).build());
                }

                applyPublicReadPolicy();

                bucketReady.set(true);
            } catch (Exception exception) {
                throw new FileUploadException("No se pudo verificar/crear el bucket en MinIO.", exception);
            }
        }
    }

    private String buildObjectName(String originalFilename) {
        String cleanName = UUID.randomUUID().toString();
        String extension = extractExtension(originalFilename);
        return "service-posts/" + cleanName + extension;
    }

    private String extractExtension(String originalFilename) {
        if (!StringUtils.hasText(originalFilename) || !originalFilename.contains(".")) {
            return "";
        }

        String extension = originalFilename.substring(originalFilename.lastIndexOf('.') + 1)
                .trim()
                .toLowerCase(Locale.ROOT);

        if (!extension.isEmpty()) {
            return "." + extension;
        }

        return "";
    }

    private String buildPublicUrl(String objectName) {
        String baseUrl = properties.getPublicUrl();
        if (StringUtils.hasText(baseUrl)) {
            return baseUrl.endsWith("/") ? baseUrl + objectName : baseUrl + "/" + objectName;
        }
        return properties.getEndpoint() + "/" + properties.getBucket() + "/" + objectName;
    }
        private void applyPublicReadPolicy() {
        try {
            String bucketName = properties.getBucket();
            String policyJson = """
                    {
                      "Version": "2012-10-17",
                      "Statement": [
                        {
                          "Effect": "Allow",
                          "Principal": {"AWS": ["*"]},
                          "Action": ["s3:GetObject"],
                          "Resource": ["arn:aws:s3:::%s/*"]
                        }
                      ]
                    }
                    """.formatted(bucketName);

            minioClient.setBucketPolicy(
                    SetBucketPolicyArgs.builder()
                            .bucket(bucketName)
                            .config(policyJson)
                            .build()
            );
        } catch (ErrorResponseException errorResponseException) {
            if ("AccessDenied".equalsIgnoreCase(errorResponseException.errorResponse().code())) {
                throw new FileUploadException("No se pudo configurar la politica publica del bucket en MinIO por falta de permisos.", errorResponseException);
            }
        } catch (Exception exception) {
            throw new FileUploadException("No se pudo configurar la politica publica del bucket en MinIO.", exception);
        }
    }
}
