package com.hurryhand.backend.dto.servicepost;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.hurryhand.backend.dto.LocationPayload;
import com.hurryhand.backend.models.Appointment;
import com.hurryhand.backend.models.Location;
import com.hurryhand.backend.models.Provider;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateServicePostDTO {

    @NotNull(message = "El titulo no puede ser nulo.")
    @Size(min = 4, max = 150, message = "El titulo debe contener entre 4 y 150 caracteres.")
    @Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚüÜñÑ\\s'\\-]*$", message = "El titulo solo puede contener letras, números y espacios.")
    private String title;

    @NotNull(message = "La descripción no puede ser nula.")
    @Size(min = 4, max = 1000, message = "La descripción debe contener entre 4 y 1000 caracteres.")
    @Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚüÜñÑ\\s'\\-]*$", message = "La descripción solo puede contener letras, números y espacios.")
    private String description;

    @NotNull(message = "Debe ingresar la duración del servicio.")
    private Integer duration;

    @NotNull(message = "El precio no puede ser nulo")
    private Integer price;

    @NotNull(message = "La ubicación no puede ser nula")
    private LocationPayload location;

    @Schema(type = "string", pattern = "yyyy-MM-dd-HH-mm-ss", example = "2025-09-30-19-27-31")
    @NotNull(message = "Debe tener un lista de fechas disponibles.")
    @JsonFormat(pattern = "yyyy-MM-dd-HH-mm-ss")
    private List<LocalDateTime> availableDates = new ArrayList<>();


}
