package com.hurryhand.backend.exceptions.credential;

import com.hurryhand.backend.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CredentialNameAlreadyInUse extends BaseException {

    public CredentialNameAlreadyInUse(String message){
        super(message);
    }
}
