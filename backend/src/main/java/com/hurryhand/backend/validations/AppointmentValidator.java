package com.hurryhand.backend.validations;


import com.hurryhand.backend.exceptions.appointment.AppointmentCreatorMatchWithServicePostOwnerException;
import com.hurryhand.backend.exceptions.appointment.DateTimeNotAvailableException;
import com.hurryhand.backend.models.ServicePost;
import com.hurryhand.backend.models.User;
import com.hurryhand.backend.services.ServicePostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AppointmentValidator {

    private final ServicePostService servicePostService;

    public void validateDateTimeIsAvailable(ServicePost servicePost, LocalDateTime localDateTime) {

        if (!servicePost.getAvailableDates().contains(localDateTime)) {

            throw new DateTimeNotAvailableException("La fecha y hora seleccionadas no están disponibles para este servicio.");
        }
    }

    public void validateAppointmentCreatorIsNotServicePostOwner(ServicePost servicePost, User user) throws AppointmentCreatorMatchWithServicePostOwnerException {

        User userProvider = servicePost.getProvider().getUser();

        if (user.equals(userProvider)){
            throw new AppointmentCreatorMatchWithServicePostOwnerException("El creador de la reunion no puede ser el que creo el servicio");
        }

    }







}
