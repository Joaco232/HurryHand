package com.hurryhand.backend.validations;

import com.hurryhand.backend.exceptions.servicepost.FailedToUploadPhotosException;
import com.hurryhand.backend.models.Credential;
import com.hurryhand.backend.models.ServicePost;
import com.hurryhand.backend.services.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MinioValidator {

    private final ProviderService providerService;

    public void validateProvierOwnsServicePost(String providerEmail, ServicePost servicePost) {

        if (!servicePost.getProvider().equals(providerService.getProviderByUserEmail(providerEmail))) {

            throw new FailedToUploadPhotosException("El proveedor no es el dueño del service post.");
        }

    }

    public void validateProviderOwnsCredencials(String providerEmail, Credential credential) {

        if (!providerEmail.equals(credential.getProvider().getUser().getEmail())) {

            throw new FailedToUploadPhotosException("El proveedor no es el dueño de las credenciales.");
        }

    }





}
