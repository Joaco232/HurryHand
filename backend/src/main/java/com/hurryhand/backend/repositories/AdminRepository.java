package com.hurryhand.backend.repositories;

import com.hurryhand.backend.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findAdminById(Long id);

    Optional<Admin> findAdminByEmail(String email);

}
