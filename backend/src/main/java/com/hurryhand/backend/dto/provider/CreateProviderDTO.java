package com.hurryhand.backend.dto.provider;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProviderDTO {

    @NotNull(message = "El id del usuario no puede ser nula")
    private Long userId;


}
