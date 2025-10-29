package com.hurryhand.backend.exceptions.servicepost;

import com.hurryhand.backend.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ServicePostHasAppointmentsException extends BaseException {
    public ServicePostHasAppointmentsException(String message) {
        super(message);
    }
}
