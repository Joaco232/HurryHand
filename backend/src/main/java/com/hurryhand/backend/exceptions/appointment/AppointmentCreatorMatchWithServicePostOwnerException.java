package com.hurryhand.backend.exceptions.appointment;

import com.hurryhand.backend.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AppointmentCreatorMatchWithServicePostOwnerException extends BaseException {
    public AppointmentCreatorMatchWithServicePostOwnerException(String message) {
        super(message);
    }
}
