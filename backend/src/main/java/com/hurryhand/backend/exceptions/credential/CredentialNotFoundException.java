package com.hurryhand.backend.exceptions.credential;

import com.hurryhand.backend.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class CredentialNotFoundException extends BaseException {
    public CredentialNotFoundException(String message) {
        super(message);
    }
}
