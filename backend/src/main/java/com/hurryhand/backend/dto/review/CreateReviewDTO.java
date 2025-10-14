package com.hurryhand.backend.dto.review;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateReviewDTO {

    @NotNull(message = "La review debe pertenecer a un appointment")
    private Long appointmentID;

    @NotNull(message = "El titulo no puede ser vacio")
    private String title;

    @Column(name = "BODY", nullable = false)
    @NotNull(message = "El body no puede ser nulo")
    private String body;

    @DecimalMin(value = "0.0", inclusive = true, message = "El rating no puede ser menor a 0.0")
    @DecimalMax(value = "5.0", inclusive = true, message = "El rating no puede ser mayor a 5.0")
    private BigDecimal rating;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @NotNull(message = "La fecha de creación no puede estar vacía")
    private LocalDateTime sentAt;
}
