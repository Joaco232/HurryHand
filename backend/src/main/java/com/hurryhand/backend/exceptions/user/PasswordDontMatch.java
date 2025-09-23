package com.hurryhand.backend.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class PasswordDontMatch extends RuntimeException {
    public PasswordDontMatch(String message) {
        super(message);
    }
}
