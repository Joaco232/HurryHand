package com.hurryhand.backend.validations;


import com.hurryhand.backend.exceptions.appointment.DateTimeNotAvailableException;
import com.hurryhand.backend.exceptions.servicepost.ServicePostNotFoundException;
import com.hurryhand.backend.models.ServicePost;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class AppointmentValidator {


    public void validateDateTimeIsAvailable(ServicePost servicePost, LocalDateTime localDateTime) {

        if (!servicePost.getAvailableDates().contains(localDateTime)) {

            throw new DateTimeNotAvailableException("La fecha y hora seleccionadas no están disponibles para este servicio.");
        }


    }







}
