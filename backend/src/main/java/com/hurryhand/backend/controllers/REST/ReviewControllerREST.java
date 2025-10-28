package com.hurryhand.backend.controllers.REST;

import com.hurryhand.backend.dto.ApiResponse;
import com.hurryhand.backend.dto.review.CreateReviewDTO;
import com.hurryhand.backend.dto.servicepost.CreateServicePostDTO;
import com.hurryhand.backend.mappers.ApiResponseMapper;
import com.hurryhand.backend.models.Appointment;
import com.hurryhand.backend.models.User;
import com.hurryhand.backend.security.CustomUserDetails;
import com.hurryhand.backend.security.CustomUserDetailsService;
import com.hurryhand.backend.services.AppointmentService;
import com.hurryhand.backend.services.ReviewService;
import com.hurryhand.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ReviewControllerREST {
    private final ReviewService reviewService;
    private final UserService userService;
    private final AppointmentService appointmentService;
    private final ApiResponseMapper apiResponseMapper;

    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse> createReview(@Valid @RequestBody CreateReviewDTO reviewDTO,
                                                    @AuthenticationPrincipal CustomUserDetails user) {

        User activeUser = userService.getUserById(user.getId());
        Appointment appointmentOfReview = appointmentService.getAppointmentById(reviewDTO.getAppointmentID());

        reviewService.createReview(reviewDTO, appointmentOfReview, activeUser);
        return apiResponseMapper.makeResponseEntity(HttpStatus.OK,"Review creada con exito");
    }

}