package com.hurryhand.backend.controllers.REST;

import com.hurryhand.backend.dto.ApiResponse;
import com.hurryhand.backend.dto.appointment.CreateAppointmentDTO;
import com.hurryhand.backend.mappers.ApiResponseMapper;
import com.hurryhand.backend.models.Appointment;
import com.hurryhand.backend.models.User;
import com.hurryhand.backend.security.CustomUserDetails;
import com.hurryhand.backend.services.AppointmentService;
import com.hurryhand.backend.services.ProviderService;
import com.hurryhand.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointment")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AppointmentControllerREST {

    private final ProviderService providerService;
    private final UserService userService;
    private final ApiResponseMapper apiResponseMapper;
    private final AppointmentService appointmentService;

    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse> createAppointment(@Valid @RequestBody CreateAppointmentDTO createAppointmentDTO,
                                                         @AuthenticationPrincipal CustomUserDetails user) {

        User actualUser = userService.getUserById(user.getId());

        appointmentService.createAppointment(createAppointmentDTO,actualUser);

        return apiResponseMapper.makeResponseEntity(HttpStatus.OK, "Appointment creado correctamente");
    }




}
