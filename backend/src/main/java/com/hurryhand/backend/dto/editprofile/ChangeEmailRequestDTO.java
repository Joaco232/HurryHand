package com.hurryhand.backend.dto.editprofile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangeEmailRequestDTO {
    @NotBlank(message = "El email no puede ser nulo.")
    @Email(message = "Formato de email no valido.")
    @Size(max = 254, message = "El email no puede superar 254 caracteres.")
    private String email;
}
