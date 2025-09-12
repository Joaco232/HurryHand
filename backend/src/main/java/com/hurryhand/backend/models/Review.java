package com.hurryhand.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "REVIEWS")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "APPOINTMENT_ID", nullable = false)
    @NotNull(message = "La review debe pertenecer a un appointment")
    private Appointment appointment;

    @Column(name = "TITLE", nullable = false, length = 30)
    @NotNull(message = "El titulo no puede ser vacio")
    private String title;

    @Column(name = "BODY", nullable = false)
    @NotNull(message = "El body no puede ser nulo")
    private String body;

    @Column(name = "RATING", precision = 2, scale = 1)
    @DecimalMin(value = "0.0", inclusive = true, message = "El rating no puede ser menor a 0.0")
    @DecimalMax(value = "5.0", inclusive = true, message = "El rating no puede ser mayor a 5.0")
    private BigDecimal rating;

    @Column(name = "UPDATED_AT", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "SENT_AT", nullable = false)
    @NotNull(message = "La fecha de creación no puede estar vacía")
    private LocalDateTime sentAt;

}




