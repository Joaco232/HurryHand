package com.hurryhand.backend.repositories;

import com.hurryhand.backend.models.Appointment;
import com.hurryhand.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findAppointmentById(Long id);


    List<Appointment> findAllByCliente(User user);

}
