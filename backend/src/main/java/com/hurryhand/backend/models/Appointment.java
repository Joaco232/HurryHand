package com.hurryhand.backend.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "APPOINTMENTS")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @JoinColumn(name = "SERVICE_POST_ID", nullable = false)
    @NotNull(message = "El ID del servicio no puede ser nulo.")
    @ManyToOne
    private ServicePost servicePost;

    @Column(name = "DATE_TIME", nullable = false)
    @NotNull(message = "La fecha y hora no pueden ser nulas.")
    @FutureOrPresent(message = "La fecha debe estar en el presente o futuro.")
    private LocalDateTime dateTime;

    @Column(name = "STATUS", nullable = false, length = 20)
    @NotNull(message = "El estado no puede ser nulo.")
    private String status;

    @Column(name = "PAYMENT_STATUS", nullable = false, length = 20)
    @NotNull(message = "El estado del pago no puede ser nulo.")
    private String paymentStatus;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    @NotNull(message = "El cliente no puede ser nulo.")
    private User cliente;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Review review;

}
