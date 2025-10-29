package com.hurryhand.backend.validations;


import com.hurryhand.backend.exceptions.servicepost.PastAvailablesDatesException;
import com.hurryhand.backend.exceptions.servicepost.ProviderDoesNotOwnServicePostException;
import com.hurryhand.backend.exceptions.servicepost.ServicePostHasAppointmentsException;
import com.hurryhand.backend.exceptions.servicepost.ServicePostTitleAlreadyInUseByProviderException;
import com.hurryhand.backend.models.Appointment;
import com.hurryhand.backend.models.Provider;
import com.hurryhand.backend.models.ServicePost;
import com.hurryhand.backend.repositories.ServicePostRepository;
import io.micrometer.core.instrument.config.validate.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ServicePostValidator {

    private final ServicePostRepository servicePostRepository;


    public void validateUniqueTitleByProvider(String title, Provider provider) {

        if (servicePostRepository.existsServicePostByTitleAndProvider(title, provider)) {

            throw new ServicePostTitleAlreadyInUseByProviderException("El provider ya tiene un service post con ese titulo.");
        }
    }

    public void validatePastAvailableDates(List<LocalDateTime> availableDates) throws PastAvailablesDatesException {

        LocalDateTime currentDateTime = LocalDateTime.now();

        for (LocalDateTime date: availableDates){
            if (date.isBefore(currentDateTime)){
                throw new PastAvailablesDatesException("Una o varias fechas del service post son anteriores a la fecha actual");
            }
        }
    }

    public void validateServicePostHasNoAppointments(List<Appointment> appointments) throws ServicePostHasAppointmentsException {

        if (!appointments.stream().filter(appointment -> appointment.getDateTime().isAfter(LocalDateTime.now())).toList().isEmpty()) {
            throw new ServicePostHasAppointmentsException("El service post tiene citas asociadas y no puede ser eliminado o modificado.");
        }
    }


    public void validateProviderOwnsServicePost(ServicePost servicePost, Long providerId) throws ProviderDoesNotOwnServicePostException {

        if (!servicePost.getProvider().getId().equals(providerId)) {

            throw new ProviderDoesNotOwnServicePostException("El provider no es dueño del service post.");
        }
    }




}
