package com.hurryhand.backend.dto.credential;

import com.hurryhand.backend.enums.CredentialStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CredentialDTO {

    @NotNull(message = "El nombre no puede ser nulo")
    private String name;

    @NotNull(message = "El providerId no puede ser nulo")
    private Long providerId;

    @NotNull(message = "El emisor no puede ser nulo")
    private String issuer;

    private String description;

    private LocalDate validUntil;

    @NotNull(message = "La fecha de emisión no puede ser nula")
    private LocalDate issuedAt;

    private LocalDate startedAt;

    private LocalDate completedAt;

    @NotNull(message = "La URL del documento no puede ser nula")
    private String documentUrl;

    @NotNull(message = "El estado de la credencial no puede ser nulo")
    private CredentialStatus credentialStatus;

}
