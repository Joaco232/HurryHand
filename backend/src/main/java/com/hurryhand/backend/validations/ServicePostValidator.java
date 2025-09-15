package com.hurryhand.backend.validations;


import com.hurryhand.backend.exceptions.servicepost.ServicePostTitleAlreadyInUseByProviderException;
import com.hurryhand.backend.models.Provider;
import com.hurryhand.backend.repositories.ServicePostRepository;
import io.micrometer.core.instrument.config.validate.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServicePostValidator {

    private final ServicePostRepository servicePostRepository;


    public void validateUniqueTitleByProvider(String title, Provider provider) {

        if (servicePostRepository.existsServicePostByTitleAndProvider(title, provider)) {

            throw new ServicePostTitleAlreadyInUseByProviderException("El provider ya tiene un service post con ese titulo.");
        }
    }




}
