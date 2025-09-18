package com.hurryhand.backend.services;

import com.hurryhand.backend.dto.credential.CreateCredentialDTO;
import com.hurryhand.backend.dto.credential.CredentialResponseDTO;
import com.hurryhand.backend.mappers.CredentialMappper;
import com.hurryhand.backend.models.Credential;
import com.hurryhand.backend.models.User;
import com.hurryhand.backend.repositories.CredentialRepository;
import com.hurryhand.backend.repositories.ProviderRepository;
import com.hurryhand.backend.models.Provider;
import com.hurryhand.backend.validations.CredentialValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CredentialService {


    private final CredentialMappper credentialMappper;
    private final CredentialRepository credentialRepository;
    private final CredentialValidator credentialValidator;

    public Credential addNewCredentailForUser(CreateCredentialDTO createCredentialDTO, Provider provider ){

        credentialValidator.validateUniqueCredentialName(createCredentialDTO.getName(), provider);

        return credentialRepository.save(credentialMappper.toEntity(createCredentialDTO, provider));

    }

    public List<CredentialResponseDTO> getCredentialRelatedToProvider(Provider provider){
        return credentialRepository.findByProviderId(provider.getId())
            .stream()
            .map(credential -> credentialMappper.toResponseDTO(credential))
            .toList();
    }

}
