package com.hurryhand.backend.mappers;

import com.hurryhand.backend.dto.review.CreateReviewDTO;
import com.hurryhand.backend.dto.review.ReviewForInfoDTO;
import com.hurryhand.backend.exceptions.attribute.NullValueForMapperException;
import com.hurryhand.backend.models.Appointment;
import com.hurryhand.backend.models.Review;
import com.hurryhand.backend.models.ServicePost;
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

    public ReviewForInfoDTO toReviewForInfoDTO(Review review) throws NullValueForMapperException {

        if (review == null){
            throw new NullValueForMapperException("El review o service post no pueden ser nulos");
        }

        return ReviewForInfoDTO.builder()
                .id(review.getId())
                .title(review.getTitle())
                .body(review.getBody())
                .rating(review.getRating().doubleValue())
                .userName(review.getAppointment().getCliente().getName())
                .userProfilePhotoURL(review.getAppointment().getCliente().getProfilePhoto())
                .build();

    }


}
