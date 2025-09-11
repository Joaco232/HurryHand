package com.hurryhand.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "CREDENTIALS")
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PROVIDER_ID", nullable = false)
    @NotNull(message = "La credencial debe pertenecer a un proveedor")
    private Provider provider;

    @Column(name = "TYPE", nullable = false, length = 50)
    @NotNull(message = "El tipo de credencial no puede ser nulo")
    private String type;

    @Column(name = "ISSUER", nullable = false, length = 100)
    @NotNull(message = "El emisor no puede ser nulo")
    private String issuer;

    @Column(name = "CREDENTIAL_CODE", nullable = false, length = 100, unique = true)
    @NotNull(message = "El código de credencial no puede ser nulo")
    private String credentialCode;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;

    @Column(name = "ISSUED_AT", nullable = false)
    private LocalDateTime issuedAt;

    @Column(name = "VALID_UNTIL")
    private LocalDateTime validUntil;

    @Column(name = "DOCUMENT_URL", length = 255)
    private String documentUrl;

    @Column(name = "STATUS", nullable = false, length = 30)
    private String status;

    @Column(name = "VERIFIED_BY", length = 100)
    private String verifiedBy;

    @Column(name = "VERIFIED_AT")
    private LocalDateTime verifiedAt;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;


}
