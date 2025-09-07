package com.hurryhand.backend.dto.user;


import com.hurryhand.backend.enums.PersonalIdType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {

    @Column(name = "EMAIL", unique = true, nullable = false)
    @NotNull(message = "El email no puede ser nulo.")
    @Email(message = "Formato de email no valido.")
    @Size(max = 254, message = "El email no puede superar 254 caracteres.")
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    @NotNull(message = "La contraseña no puede ser nula.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#!$%^&+=])(?=\\S+$).{8,}$",
            message = "La contraseña debe contener al menos una letra mayúscula, una minúscula, un número y un carácter especial")
    @Size(min = 8, max = 254, message = "La contraseña debe tener entre 8 y 254 caracteres.")
    private String password;

    @Column(name = "NAME", nullable = false, length = 50)
    @NotNull(message = "El nombre no puede ser nulo.")
    @Size(min = 1, max = 50, message = "El nombre debe contener entre 1 y 50 caracteres.")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s'\\-]*$", message = "The name must only contain letters and spaces.")
    private String name;

    @Column(name = "SURNAME", nullable = false, length = 50)
    @NotNull(message = "El apellido no puede ser nulo.")
    @Size(min = 1, max = 50, message = "El apellido debe contener entre 1 y 50 caracteres.")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s'\\-]*$", message = "The surname must only contain letters and spaces.")
    private String surname;

    @Column(name = "PERSONAL_ID", nullable = false, length = 50)
    @NotNull(message = "El identificador personal no puede ser nulo.")
    @Size(min = 1, max = 50, message = "El identificador personal debe tener entre 1 y 70 caracteres.")
    @Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚüÜñÑ\\s'\\-]*$", message = "El identificador personal puede contener letras, números y guiones.")
    private String personalId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El tipo de identificador personal no puede ser nulo.")
    @Column(name = "PERSONAL_ID_TYPE")
    private PersonalIdType personalIdType;

    @Column(name = "BIRTHDATE", nullable = false)
    @NotNull(message = "La fecha de nacimiento no puede ser nula.")
    @Past(message = "La fecha de nacimiento debe estar en pasado.")
    private LocalDate birthdate;

}
