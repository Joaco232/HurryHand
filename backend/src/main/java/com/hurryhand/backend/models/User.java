package com.hurryhand.backend.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Table(name = "USERS")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends BaseUser {

    @Column(name = "BIRTHDATE", nullable = false)
    @NotNull(message = "La fecha de nacimiento no puede ser nula.")
    @Past(message = "La fecha de nacimiento debe estar en pasado.")
    private LocalDate birthdate;

    @Column(name = "PROFILE_PHOTO", length = 254)
    @Size(max = 254, message = "La ruta de la foto de perfil no puede superar 254 caracteres.")
    private String profilePhoto;

    @Embedded
    private Location location;

    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Provider provider;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();



}
