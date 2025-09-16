package com.hurryhand.backend.exceptions.attribute;

import com.hurryhand.backend.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NullValueForMapperException extends BaseException {
    public NullValueForMapperException(String message) {
        super(message);
    }
}
