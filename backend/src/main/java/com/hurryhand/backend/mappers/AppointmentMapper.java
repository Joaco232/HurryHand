package com.hurryhand.backend.mappers;


import com.hurryhand.backend.dto.appointment.AppointmentShowDTO;
import com.hurryhand.backend.dto.appointment.CreateAppointmentDTO;
import com.hurryhand.backend.enums.AppointmentStatus;
import com.hurryhand.backend.enums.PaymentStatus;
import com.hurryhand.backend.exceptions.attribute.NullValueForMapperException;
import com.hurryhand.backend.models.Appointment;
import com.hurryhand.backend.models.ServicePost;
import com.hurryhand.backend.models.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {


    public Appointment toEntity(CreateAppointmentDTO createAppointmentDTO, ServicePost servicePost, User user) {

        if (createAppointmentDTO == null || servicePost == null || user == null) {
            throw new NullValueForMapperException("Algun parametro es null");
        }

        return Appointment.builder()
                .cliente(user)
                .servicePost(servicePost)
                .dateTime(createAppointmentDTO.getDateTime())
                .status(AppointmentStatus.PENDING)
                .paymentStatus(PaymentStatus.PENDING)
                .build();
    }

    public AppointmentShowDTO toShowDTO(Appointment appointment) {

        if (appointment == null) {
            throw new NullValueForMapperException("El parametro es null");
        }

        return AppointmentShowDTO.builder()
                .servicePostTitle(appointment.getServicePost().getTitle())
                .servicePostId(appointment.getServicePost().getId())
                .appointmentId(appointment.getId())
                .appointmentDateTime(appointment.getDateTime())
                .durationMinutes(appointment.getServicePost().getDuration())
                .status(appointment.getStatus())
                .paymentStatus(appointment.getPaymentStatus())
                .build();
    }


}


