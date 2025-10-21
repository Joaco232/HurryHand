package com.hurryhand.backend.dto.editprofile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordRequestDTO {
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?=\\S+$).{8,}$",
            message = "La contraseña debe contener al menos una letra mayúscula, una minúscula, un número y un carácter especial")
    @NotNull(message = "La contraseña no puede ser nula.")
    @Size(min = 8, max = 254, message = "La contraseña debe tener entre 8 y 254 caracteres.")
    private String currentPassword;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-  zA-Z0-9])(?=\\S+$).{8,}$",
            message = "La contraseña debe contener al menos una letra mayúscula, una minúscula, un número y un carácter especial")
    @NotNull(message = "La contraseña no puede ser nula.")
    @Size(min = 8, max = 254, message = "La contraseña debe tener entre 8 y 254 caracteres.")
    private String newPassword;
}
