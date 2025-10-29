package com.hurryhand.backend.validations;


import com.hurryhand.backend.exceptions.servicepost.*;
import com.hurryhand.backend.models.Appointment;
import com.hurryhand.backend.models.Provider;
import com.hurryhand.backend.models.ServicePost;
import com.hurryhand.backend.repositories.ServicePostRepository;
import io.micrometer.core.instrument.config.validate.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    public void validateNewDateHasNoConflict(List<LocalDateTime> availableDates, List<LocalDateTime> appointmentDates,
                                             LocalDateTime newDate, Integer durationMin) throws DateConflictsException {

        boolean hasConflict = Stream.of(availableDates, appointmentDates)
                .filter(list -> list != null)
                .flatMap(list -> list.stream())
                .anyMatch(date -> doIntervalsOverlap(
                        date,
                        date.plusMinutes(durationMin),
                        newDate,
                        newDate.plusMinutes(durationMin)
                ));

        if (hasConflict) {

            throw new DateConflictsException("Fechas en conflicto.");
        }

    }

    private Boolean doIntervalsOverlap(LocalDateTime date1Start, LocalDateTime date1End,
                                       LocalDateTime date2Start, LocalDateTime date2End) {

        if (date2End.isBefore(date1Start)) {
            return false;
        }

        if (date1End.isBefore(date2Start)) {
            return false;
        }

        return true;
    }



}
