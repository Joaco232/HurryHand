package com.hurryhand.backend.dto.editprofile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangeNameRequestDTO {

    @NotNull(message = "El nombre no puede ser nulo.")
    @Size(min = 1, max = 50, message = "El nombre debe contener entre 1 y 50 caracteres.")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s'\\-]*$", message = "The name must only contain letters and spaces.")
    private String name;

}

