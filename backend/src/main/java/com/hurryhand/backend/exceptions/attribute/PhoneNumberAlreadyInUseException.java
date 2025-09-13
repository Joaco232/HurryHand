package com.hurryhand.backend.exceptions.attribute;

import com.hurryhand.backend.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PhoneNumberAlreadyInUseException extends BaseException {

    public PhoneNumberAlreadyInUseException(String message) {
        super(message);
    }
}
