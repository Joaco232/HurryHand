package com.hurryhand.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Table(name = "SERVICE_POSTS")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ServicePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",unique = true, nullable = false)
    private Long id;

    @Column(name = "TITLE", nullable = false, length = 150)
    @NotNull(message = "El titulo no puede ser nulo.")
    @Size(min = 4, max = 150, message = "El titulo debe contener entre 4 y 150 caracteres.")
    @Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚüÜñÑ\\s'\\-]*$", message = "El titulo solo puede contener letras, números y espacios.")
    private String title;

    @Column(name = "DESCRIPTION", nullable = false, length = 1000)
    @NotNull(message = "La descripción no puede ser nula.")
    @Size(min = 4, max = 1000, message = "La descripción debe contener entre 4 y 1000 caracteres.")
    @Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚüÜñÑ\\s'\\-]*$", message = "La descripción solo puede contener letras, números y espacios.")
    private String description;

    @Column(name = "RATING", precision = 2, scale = 1)
    private BigDecimal rating;

    @ManyToOne
    @JoinColumn(name = "PROVIDER_ID", nullable = false)
    @NotNull(message = "El servicio debe pertenecer a un proveedor")
    private Provider provider;

    @Column(name = "PRICE", nullable = false)
    @NotNull(message = "El precio no puede ser nulo")
    private Integer price;

    @Embedded
    @NotNull(message = "La ubicacion no puede ser nula")
    private Location location;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

    @ElementCollection
    @CollectionTable(
            name = "AVAILABLE_DATES",
            joinColumns = @JoinColumn(name = "SERVICE_POST_ID")
    )
    @Column(name = "DATE_TIME", nullable = false)
    private List<LocalDateTime> availableDates = new ArrayList<>();

    @OneToMany(mappedBy = "servicePost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

}
