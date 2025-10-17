package com.hurryhand.backend.validations;

import com.hurryhand.backend.exceptions.Review.AppointmentReviewAccessException;
import com.hurryhand.backend.exceptions.Review.ReviewerNotProviderOfAppointmentException;
import com.hurryhand.backend.models.Appointment;
import com.hurryhand.backend.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewValidator {

    public void ReviewerNotProvider(Appointment appointment, User user) throws ReviewerNotProviderOfAppointmentException {

        if (appointment.getServicePost().getProvider().getUser().equals(user)){

            throw new ReviewerNotProviderOfAppointmentException("El ususario que crea la review no puede ser el que brinda el servicio");

        }
    }

    public void UserAppointmentReview(Appointment appointment, User user) throws AppointmentReviewAccessException {

        if (!appointment.getCliente().equals(user)){

            throw new AppointmentReviewAccessException("El usuario no pertenece a esa appointment para hacer una review");

        }

    }

}
