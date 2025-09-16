package com.hurryhand.backend.dto.credential;

import com.hurryhand.backend.enums.CredentialStatus;
import com.hurryhand.backend.models.Provider;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CredentialResponseDTO {
    private Long id;
    private String name;
    private Long providerId;
    private String issuer;
    private String description;
    private LocalDate validUntil;
    private LocalDate issuedAt;
    private LocalDate startedAt;
    private LocalDate completedAt;
    private String documentUrl;
    private CredentialStatus credentialStatus;
    private String verifiedBy;
    private LocalDateTime verifiedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
