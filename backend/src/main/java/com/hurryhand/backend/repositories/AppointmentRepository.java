package com.hurryhand.backend.repositories;

import com.hurryhand.backend.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findAppointmentById(Long id);

}
