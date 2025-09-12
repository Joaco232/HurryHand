package com.hurryhand.backend.repositories;

import com.hurryhand.backend.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Long> {


    Optional<Provider> findProviderById(Long id);






}
