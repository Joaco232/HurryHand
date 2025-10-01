package com.hurryhand.backend.exceptions.servicepost;


import com.hurryhand.backend.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FailedToUploadPhotosException extends BaseException {

    public FailedToUploadPhotosException(String message) {
        super(message);
    }
}
