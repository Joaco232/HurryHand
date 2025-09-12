package com.hurryhand.backend.models;

import com.hurryhand.backend.enums.CredentialStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
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

    @Column(name = "ISSUER", nullable = false, length = 100)
    @NotNull(message = "El emisor no puede ser nulo")
    private String issuer;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;

    @Column(name = "VALID_UNTIL")
    private LocalDate validUntil;

    @Column(name = "ISSUED_AT", nullable = false)
    private LocalDate issuedAt;

    @Column(name = "STARTED_DATE")
    private LocalDate startedAt;

    @Column(name = "completion date")
    private LocalDate completedAt;

    @Column(name = "DOCUMENT_URL", length = 255)
    private String documentUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 30)
    private CredentialStatus credentialStatus;

    @Column(name = "VERIFIED_BY", length = 100)
    private String verifiedBy;

    @Column(name = "VERIFIED_AT")
    private LocalDateTime verifiedAt;

    @Column(name = "CREATED_AT", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
