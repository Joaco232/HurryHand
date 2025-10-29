package com.hurryhand.backend.repositories;

import com.hurryhand.backend.models.Provider;
import com.hurryhand.backend.models.ServicePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ServicePostRepository extends JpaRepository<ServicePost, Long> {


    Optional<ServicePost> findServicePostById(Long id);

    Optional<ServicePost> findServicePostByTitleAndProvider(String title,  Provider provider);

    Page<ServicePost> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    @Query(
            value = "SELECT * FROM SERVICE_POSTS " +
                    "WHERE to_tsvector('spanish', title || ' ' || description) @@ to_tsquery('spanish', :query)",
            countQuery = "SELECT count(*) FROM service_post " +
                    "WHERE to_tsvector('spanish', title || ' ' || description) @@ to_tsquery('spanish', :query)",
            nativeQuery = true
    )
    Page<ServicePost> searchFullText(@Param("query") String query, Pageable pageable);

    boolean existsServicePostByTitleAndProvider(String title,  Provider provider);

    List<ServicePost> findAllByProviderId(Long providerId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(
            value = "DELETE FROM available_dates " +
                    "WHERE service_post_id = :servicePostId " +
                    "AND date_time = :dateTime",
            nativeQuery = true
    )
    int deleteAvailableDate(@Param("servicePostId") Long servicePostId,
                            @Param("dateTime") LocalDateTime dateTime);



}
