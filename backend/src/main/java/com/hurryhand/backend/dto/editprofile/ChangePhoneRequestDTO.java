package com.hurryhand.backend.dto.editprofile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ChangePhoneRequestDTO {
    @NotNull(message = "El número de teléfono no puede ser nulo.")
    @Pattern(regexp = "^\\+?[0-9]+$",
            message = "El número de teléfono solo puede llevar números y un +.")
    private String phoneNumber;
}
