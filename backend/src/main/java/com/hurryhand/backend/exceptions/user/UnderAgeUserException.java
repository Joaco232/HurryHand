package com.hurryhand.backend.exceptions.user;


import com.hurryhand.backend.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class UnderAgeUserException extends BaseException {

    public UnderAgeUserException(String message) {
        super(message);
    }
}
