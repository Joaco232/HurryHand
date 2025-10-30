package com.hurryhand.backend.services;

import com.hurryhand.backend.dto.appointment.AppointmentShowDTO;
import com.hurryhand.backend.dto.appointment.CreateAppointmentDTO;
import com.hurryhand.backend.exceptions.appointment.AppointmentNotFoundException;
import com.hurryhand.backend.mappers.AppointmentMapper;
import com.hurryhand.backend.models.Appointment;
import com.hurryhand.backend.models.ServicePost;
import com.hurryhand.backend.models.User;
import com.hurryhand.backend.repositories.AppointmentRepository;
import com.hurryhand.backend.repositories.ServicePostRepository;
import com.hurryhand.backend.validations.AppointmentValidator;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ServicePostRepository servicePostRepository ;
    private final ServicePostService servicePostService;
    private final AppointmentMapper appointmentMapper;
    private final AppointmentValidator appointmentValidator;

    @Transactional
    public void createAppointment(@Valid CreateAppointmentDTO createAppointmentDTO, User user) {

        ServicePost servicePost = servicePostService.getServicePostById(createAppointmentDTO.getServicePostId());

        appointmentValidator.validateAppointmentCreatorIsNotServicePostOwner(servicePost, user);
        appointmentValidator.validateDateTimeIsAvailable(servicePost, createAppointmentDTO.getDateTime());

        servicePostRepository.deleteAvailableDate(
                createAppointmentDTO.getServicePostId(),
                createAppointmentDTO.getDateTime()
        );

        servicePost.getAvailableDates().remove(createAppointmentDTO.getDateTime());

        appointmentRepository.save(appointmentMapper.toEntity(createAppointmentDTO, servicePost, user));

    }

    public List<AppointmentShowDTO> getAppointmentsToShowByUser(User user) {

        List<Appointment> appointments = appointmentRepository.findAllByCliente(user);

        return appointments.stream().map(appointment -> appointmentMapper.toShowDTO(appointment)).toList();
    }

    public Appointment getAppointmentById(Long appointmentId) throws AppointmentNotFoundException {
        return appointmentRepository.findById(appointmentId).orElseThrow(()-> new AppointmentNotFoundException("El appointment con la id" + appointmentId + "no existe"));
    }



    public List<AppointmentShowDTO> getPastAppointmentsToShowByUser(User user) {

        List<Appointment> allAppointments = appointmentRepository.findAllByCliente(user);

        LocalDateTime now = LocalDateTime.now();

        return allAppointments.stream().filter(appoint -> appoint.getDateTime().isBefore(now)).map(appoint->appointmentMapper.toShowDTO(appoint)).toList();

    }





}
