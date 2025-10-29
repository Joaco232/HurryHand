package com.hurryhand.backend.exceptions.servicepost;

import com.hurryhand.backend.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DateConflictsException extends BaseException {
    public DateConflictsException(String message) {
        super(message);
    }
}
