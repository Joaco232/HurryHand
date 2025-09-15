package com.hurryhand.backend.exceptions.servicepost;

import com.hurryhand.backend.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.CONFLICT)
public class ServicePostTitleAlreadyInUseByProviderException extends BaseException {

    public ServicePostTitleAlreadyInUseByProviderException(String message) {
        super(message);
    }
}
