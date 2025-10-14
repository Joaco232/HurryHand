package com.hurryhand.backend.services;

import com.hurryhand.backend.dto.appointment.AppointmentShowDTO;
import com.hurryhand.backend.dto.appointment.CreateAppointmentDTO;
import com.hurryhand.backend.exceptions.user.UserNotFoundException;
import com.hurryhand.backend.mappers.AppointmentMapper;
import com.hurryhand.backend.models.Appointment;
import com.hurryhand.backend.models.ServicePost;
import com.hurryhand.backend.models.User;
import com.hurryhand.backend.repositories.AppointmentRepository;
import com.hurryhand.backend.repositories.ServicePostRepository;
import com.hurryhand.backend.repositories.UserRepository;
import com.hurryhand.backend.validations.AppointmentValidator;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ServicePostRepository servicePostRepository ;
    private final UserService userService;
    private final ServicePostService servicePostService;
    private final AppointmentMapper appointmentMapper;
    private final AppointmentValidator appointmentValidator;

    @Transactional
    public void createAppointment(@Valid CreateAppointmentDTO createAppointmentDTO, User user) {

        ServicePost servicePost = servicePostService.getServicePostById(createAppointmentDTO.getServicePostId());

        appointmentValidator.validateDateTimeIsAvailable(servicePost, createAppointmentDTO.getDateTime());

        servicePost.getAvailableDates().remove(createAppointmentDTO.getDateTime());

        servicePostRepository.save(servicePost);

        appointmentRepository.save(appointmentMapper.toEntity(createAppointmentDTO, servicePost, user));

    }

    public List<AppointmentShowDTO> getAppointmentsToShowByUser(User user) {

        List<Appointment> appointments = appointmentRepository.findAllByCliente(user);

        return appointments.stream().map(appointment -> appointmentMapper.toShowDTO(appointment)).toList();
    }

    public Appointment getAppointmentById(Long appointmentId) throws Appointment {
        return appointmentRepository.findById(appointmentId).orElse(null);
    }







}
