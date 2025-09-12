package com.hurryhand.backend.repositories;

import com.hurryhand.backend.models.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, Long> {


    Optional<Credential> findCredentialById(Long id);


}
