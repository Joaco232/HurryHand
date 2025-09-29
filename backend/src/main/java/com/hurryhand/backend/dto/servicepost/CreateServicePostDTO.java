package com.hurryhand.backend.dto.servicepost;

import com.hurryhand.backend.enums.DepartamentoUY;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateServicePostDTO {

    @NotBlank(message = "El titulo no puede estar vacio.")
    @Size(min = 4, max = 150, message = "El titulo debe contener entre 4 y 150 caracteres.")
    @Pattern(regexp = "^[\\p{L}0-9 '\\-]*$", message = "El titulo solo puede contener letras, numeros y espacios.")
    private String title;

    @NotBlank(message = "La descripcion no puede estar vacia.")
    @Size(min = 4, max = 1000, message = "La descripcion debe contener entre 4 y 1000 caracteres.")
    @Pattern(regexp = "^[\\p{L}0-9 '\\-]*$", message = "La descripcion solo puede contener letras, numeros y espacios.")
    private String description;

    @NotNull(message = "El precio no puede ser nulo")
    @Min(value = 0, message = "El precio no puede ser negativo.")
    private Integer price;

    @NotNull(message = "Debe indicar la duracion en minutos.")
    @Min(value = 1, message = "La duracion debe ser mayor a cero.")
    private Integer durationInMinutes;

    private LocationPayload location;

    @NotNull(message = "Debe indicar las fechas disponibles (puede ser una lista vacia).")
    @Builder.Default
    private List<LocalDateTime> availableDates = new ArrayList<>();

    @NotNull(message = "Debe indicar las fotos (puede ser una lista vacia).")
    @Builder.Default
    private List<String> photosURLs = new ArrayList<>();

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LocationPayload {
        private DepartamentoUY departamento;
        private String neighbourhood;
        private String street;
        private Integer streetNumber;
        private Integer postalCode;
        private Integer aptoNumber;
    }
}
