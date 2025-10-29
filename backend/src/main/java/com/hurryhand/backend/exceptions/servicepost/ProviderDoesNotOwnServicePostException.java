package com.hurryhand.backend.exceptions.servicepost;

import com.hurryhand.backend.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ProviderDoesNotOwnServicePostException extends BaseException {
    public ProviderDoesNotOwnServicePostException(String message) {
        super(message);
    }
}
