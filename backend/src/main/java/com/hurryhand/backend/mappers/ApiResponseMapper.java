package com.hurryhand.backend.mappers;

import com.hurryhand.backend.dto.ApiError;
import com.hurryhand.backend.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Map;

public class ApiResponseMapper {


    public ResponseEntity<ApiResponse> makeResponseEntity(HttpStatus status, String message) {

        ApiResponse apiResponse = ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .message(message)
                .build();

        return new ResponseEntity<>(apiResponse, status);
    }

}
