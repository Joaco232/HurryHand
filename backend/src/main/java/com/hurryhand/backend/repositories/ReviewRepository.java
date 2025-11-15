package com.hurryhand.backend.repositories;

import com.hurryhand.backend.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findReviewById(Long id);

    List<Review> findAllByAppointment_ServicePost_Id(Long servicePostId);

}
