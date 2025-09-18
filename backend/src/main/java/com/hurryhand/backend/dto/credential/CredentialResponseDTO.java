package com.hurryhand.backend.dto.credential;

import com.hurryhand.backend.enums.CredentialStatus;
import com.hurryhand.backend.models.Provider;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CredentialResponseDTO {
    private Long id;
    private String name;
    private String issuer;
    private String description;
    private LocalDate validUntil;
    private LocalDate issuedAt;
    private LocalDate startedAt;
    private LocalDate completedAt;
    private String certificateUrl;
    private CredentialStatus credentialStatus;
    private String verifiedBy;
    private LocalDateTime verifiedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
