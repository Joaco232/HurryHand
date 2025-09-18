package com.hurryhand.backend.validations;

import com.hurryhand.backend.exceptions.credential.CredentialNameAlreadyInUse;
import com.hurryhand.backend.models.Provider;
import com.hurryhand.backend.repositories.CredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CredentialValidator {

    private final CredentialRepository credentialRepository;

    public void validateUniqueCredentialName(String credentialName, Provider provider){
        if (credentialRepository.existsCredentialByNameAndProvider(credentialName, provider)){
            throw new CredentialNameAlreadyInUse("Ya existe una credencial con ese nombre para este usuario.");
        }
    }

}
