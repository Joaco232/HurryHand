package com.hurryhand.backend.dto.appointment;

import com.hurryhand.backend.enums.AppointmentStatus;
import com.hurryhand.backend.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentShowDTO {

    private String servicePostTitle;

    private Long servicePostId;

    private Long appointmentId;

    private LocalDateTime appointmentDateTime;

    private Integer durationMinutes;

    private AppointmentStatus status;

    private PaymentStatus paymentStatus;

}
