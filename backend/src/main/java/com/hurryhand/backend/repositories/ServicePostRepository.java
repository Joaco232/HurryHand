package com.hurryhand.backend.repositories;

import com.hurryhand.backend.models.ServicePost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicePostRepository extends JpaRepository<ServicePost, Long> {


    Optional<ServicePost> findServicePostById(Long id);

}
