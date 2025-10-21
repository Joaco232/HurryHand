package com.hurryhand.backend.services;


import com.hurryhand.backend.dto.review.CreateReviewDTO;
import com.hurryhand.backend.mappers.ReviewMapper;
import com.hurryhand.backend.models.Appointment;
import com.hurryhand.backend.models.Review;
import com.hurryhand.backend.models.ServicePost;
import com.hurryhand.backend.models.User;
import com.hurryhand.backend.repositories.AppointmentRepository;
import com.hurryhand.backend.repositories.ReviewRepository;
import com.hurryhand.backend.repositories.ServicePostRepository;
import com.hurryhand.backend.validations.ReviewValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final ReviewValidator reviewValidator;
    private final ServicePostRepository servicePostRepository;
    private final ServicePostService servicePostService;
    private final AppointmentRepository appointmentRepository;

    @Transactional
    public void createReview(@Valid CreateReviewDTO createReviewDTO, Appointment appointment, User user) {

        reviewValidator.ReviewerNotProvider(appointment, user);

        reviewValidator.UserAppointmentReview(appointment, user);

        Review review = reviewMapper.ToEntity(createReviewDTO, appointment);

        reviewRepository.save(review);

        appointment.setReview(review);

        ServicePost servicePost = appointment.getServicePost();

        servicePost.updateRating();

        servicePostRepository.save(servicePost);
    }

}
