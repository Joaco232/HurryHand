package com.hurryhand.backend.dto.credential;

import com.hurryhand.backend.enums.CredentialStatus;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCredentialDTO {

    @NotNull(message = "El nombre no puede ser nulo")
    private String name;

    @NotNull(message = "El emisor no puede ser nulo")
    private String issuer;

    private String description;

    private LocalDate validUntil;

    @NotNull(message = "La fecha de emisión no puede ser nula")
    private LocalDate issuedAt;

    private LocalDate startedAt;

    private LocalDate completedAt;

    @NotNull(message = "La URL del documento no puede ser nula")
    private String certificateUrl;

    //@NotNull(message = "El estado de la credencial no puede ser nulo")
    //private CredentialStatus credentialStatus;

}
