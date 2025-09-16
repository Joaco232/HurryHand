package com.hurryhand.backend.exceptions.servicepost;

import com.hurryhand.backend.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ServicePostNotFoundException extends BaseException {
    public ServicePostNotFoundException(String message) {
        super(message);
    }
}
