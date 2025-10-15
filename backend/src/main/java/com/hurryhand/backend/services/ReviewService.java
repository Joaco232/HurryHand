package com.hurryhand.backend.services;


import com.hurryhand.backend.dto.review.CreateReviewDTO;
import com.hurryhand.backend.mappers.ReviewMapper;
import com.hurryhand.backend.models.Appointment;
import com.hurryhand.backend.models.User;
import com.hurryhand.backend.repositories.ReviewRepository;
import com.hurryhand.backend.validations.ReviewValidator;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final ReviewValidator reviewValidator;

    @Transactional
    public void createReview(@Valid CreateReviewDTO createReviewDTO, Appointment appointment, User user) {

        reviewValidator.ReviewerNotProvider(appointment, user);

        reviewValidator.UserAppointmentReview(appointment, user);

        reviewRepository.save(reviewMapper.ToEntity(createReviewDTO, appointment));

    }

}
