package com.hurryhand.backend.exceptions.user;

import com.hurryhand.backend.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class UserNotFoundException extends BaseException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
