package com.hurryhand.backend.services;

import com.hurryhand.backend.config.MinioProperties;
import com.hurryhand.backend.exceptions.servicepost.FailedToUploadPhotosException;
import com.hurryhand.backend.models.Credential;
import com.hurryhand.backend.models.Provider;
import com.hurryhand.backend.models.ServicePost;
import com.hurryhand.backend.validations.MinioValidator;
import io.minio.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.hurryhand.backend.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;
    private final MinioProperties properties;
    private final UserService userService;
    private final ServicePostService servicePostService;
    private final CredentialService credentialService;
    private final MinioValidator minioValidator;

    public String uploadUserProfilePhoto(User user, MultipartFile file) {

        try {

            if (file == null || file.isEmpty()) {
                throw new IllegalArgumentException("El archivo de imagen no puede estar vacío.");
            }

            String objectName = user.getEmail() + "/profile_photo";

            checkBucketExists();

            // Subir archivo
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(properties.getBucket())
                            .object(objectName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );


            userService.changeProfilePhoto(user, getPublicUrl(objectName));

            return getPublicUrl(objectName);

        } catch (Exception e) {

            throw new FailedToUploadPhotosException("No se ha podido subir la foto de perfil." + e.getMessage());
        }
    }

    public String uploadCredentialPhoto(Credential credential, MultipartFile file, String email) {

        minioValidator.validateProviderOwnsCredencials(email, credential);

        try {

            if (file == null || file.isEmpty()) {
                throw new IllegalArgumentException("El archivo de imagen no puede estar vacío.");
            }

            String objectName = credential.getProvider().getUser().getEmail() + "/credentials/" + credential.getId();

            checkBucketExists();

            // Subir archivo
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(properties.getBucket())
                            .object(objectName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            credentialService.addCertificateToCredential(getPublicUrl(objectName), credential);

            return getPublicUrl(objectName);

        } catch (Exception e) {

            throw new FailedToUploadPhotosException("No se ha podido subir la foto de perfil.");
        }
    }


    private void checkBucketExists() throws Exception {

        boolean found = minioClient.bucketExists(
                BucketExistsArgs.builder().bucket(properties.getBucket()).build()
        );

        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(properties.getBucket()).build());
            applyPublicReadPolicy();
        }
    }


    public List<String> uploadServicePostPhotos(String providerEmail, ServicePost servicePost, List<MultipartFile> files) {

        try {

            if (files == null || files.isEmpty()) {
                throw new IllegalArgumentException("El archivo de imagen no puede estar vacío.");
            }

            minioValidator.validateProvierOwnsServicePost(providerEmail, servicePost);


            List<String> urls = new ArrayList<>();

            checkBucketExists();

            for (int i = 0; i < files.size(); i++) {

                MultipartFile file = files.get(i);
                String objectName = providerEmail + "/service-post-" + servicePost.getId() + "/photo-" + i;
                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(properties.getBucket())
                                .object(objectName)
                                .stream(file.getInputStream(), file.getSize(), -1)
                                .contentType(file.getContentType())
                                .build()
                );
                urls.add(getPublicUrl(objectName));

            }

            servicePostService.uploadPhotosURLsOfServicePost(servicePost, urls);

            return urls;

        } catch (Exception e) {

            throw new FailedToUploadPhotosException(e.getMessage());
        }
    }

    public List<String> uploadPhotosOnly(String providerEmail, List<MultipartFile> files) {

        try {

            if (files == null || files.isEmpty()) {
                throw new IllegalArgumentException("El archivo de imagen no puede estar vacío.");
            }

            List<String> urls = new ArrayList<>();

            checkBucketExists();

            for (int i = 0; i < files.size(); i++) {

                MultipartFile file = files.get(i);
                // Generar nombre único sin servicePostId
                String timestamp = String.valueOf(System.currentTimeMillis());
                String objectName = providerEmail + "/temp-photos/" + timestamp + "-photo-" + i;
                
                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(properties.getBucket())
                                .object(objectName)
                                .stream(file.getInputStream(), file.getSize(), -1)
                                .contentType(file.getContentType())
                                .build()
                );
                urls.add(getPublicUrl(objectName));

            }

            // No llamamos a servicePostService porque aún no existe el service post
            return urls;

        } catch (Exception e) {

            throw new FailedToUploadPhotosException(e.getMessage());
        }
    }


    private String getPublicUrl(String objectName) {

        String baseUrl = properties.getPublicUrl();

        if (baseUrl.endsWith("/")) {
            return baseUrl + properties.getBucket() + "/" + objectName;
        }

        return baseUrl + "/" + properties.getBucket() + "/" + objectName;
    }


    private void applyPublicReadPolicy() throws Exception {
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
    }


}
