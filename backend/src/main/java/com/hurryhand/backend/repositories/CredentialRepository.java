package com.hurryhand.backend.repositories;

import com.hurryhand.backend.models.Credential;
import com.hurryhand.backend.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, Long> {


    Optional<Credential> findCredentialById(Long id);

    List<Credential> findByProviderId(Long providerId);

    boolean existsCredentialByNameAndProvider(String name, Provider provider);

}
