package com.hurryhand.backend.dto.appointment;


import com.hurryhand.backend.enums.AppointmentStatus;
import com.hurryhand.backend.enums.PaymentStatus;
import com.hurryhand.backend.models.Review;
import com.hurryhand.backend.models.ServicePost;
import com.hurryhand.backend.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAppointmentDTO {

    @NotNull(message = "El ID del servicio no puede ser nulo.")
    private Long servicePostId;

    @NotNull(message = "La fecha y hora no pueden ser nulas.")
    //@FutureOrPresent(message = "La fecha debe estar en el presente o futuro.")
    private LocalDateTime dateTime;

}
