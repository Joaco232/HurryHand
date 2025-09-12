package com.hurryhand.backend.exceptions.Provider;

import com.hurryhand.backend.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyProviderException extends BaseException {

    public UserAlreadyProviderException(String message) {
        super(message);
    }
}
