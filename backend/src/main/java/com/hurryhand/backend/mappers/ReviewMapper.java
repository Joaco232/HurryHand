package com.hurryhand.backend.mappers;

import com.hurryhand.backend.dto.review.CreateReviewDTO;
import com.hurryhand.backend.exceptions.attribute.NullValueForMapperException;
import com.hurryhand.backend.models.Appointment;
import com.hurryhand.backend.models.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public Review ToEntity(CreateReviewDTO dto, Appointment appointment) throws NullValueForMapperException {

        if (dto == null || appointment == null){
            throw new NullValueForMapperException("El DTO no puede ser nulo");
        }
        return Review.builder()
                .appointment(appointment)
                .title(dto.getTitle())
                .body(dto.getBody())
                .rating(dto.getRating())
                .updatedAt(dto.getUpdatedAt())
                .sentAt(dto.getUpdatedAt())
                .build();
    }


}
