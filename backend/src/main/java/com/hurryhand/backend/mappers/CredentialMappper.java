package com.hurryhand.backend.mappers;

import com.hurryhand.backend.dto.credential.CreateCredentialDTO;
import com.hurryhand.backend.dto.credential.CredentialResponseDTO;
import com.hurryhand.backend.exceptions.attribute.NullValueForMapperException;
import com.hurryhand.backend.models.Credential;
import com.hurryhand.backend.models.Provider;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public class CredentialMappper {

    public Credential toEntity(CreateCredentialDTO dto, Provider provider){

        if (dto == null || provider == null){
            throw new NullValueForMapperException("DTO o Provider es null al mapear Credential.");
        }
        return Credential.builder()
            .name(dto.getName())
            .issuer(dto.getIssuer())
            .description(dto.getDescription())
            .validUntil(dto.getValidUntil())
            .issuedAt(dto.getIssuedAt())
            .startedAt(dto.getStartedAt())
            .completedAt(dto.getCompletedAt())
            .credentialStatus(dto.getCredentialStatus())
            .certificateUrl(dto.getCertificateUrl())
            .provider(provider)
            .build();
        }

    public CredentialResponseDTO toResponseDTO(Credential credential){
        if (credential ==  null){
            throw new NullValueForMapperException("La credencial es null en el mapper");
        }
    return CredentialResponseDTO.builder()
        .id(credential.getId())
        .name(credential.getName())
        .issuer(credential.getIssuer())
        .description(credential.getDescription())
        .validUntil(credential.getValidUntil())
        .issuedAt(credential.getIssuedAt())
        .startedAt(credential.getStartedAt())
        .completedAt(credential.getCompletedAt())
        .credentialStatus(credential.getCredentialStatus())
        .certificateUrl(credential.getCertificateUrl())
        .verifiedBy(credential.getVerifiedBy())
        .verifiedAt(credential.getVerifiedAt())
        .createdAt(credential.getCreatedAt())
        .updatedAt(credential.getUpdatedAt())
        .build();
    }

}

