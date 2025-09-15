package com.hurryhand.backend.controllers.REST;


import com.hurryhand.backend.dto.servicepost.CreateServicePostDTO;
import com.hurryhand.backend.models.Provider;
import com.hurryhand.backend.security.CustomUserDetails;
import com.hurryhand.backend.services.ProviderService;
import com.hurryhand.backend.services.ServicePostService;
import com.hurryhand.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/servicepost")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class ServicePostControllerREST {

    private final ServicePostService servicePostService;
    private final UserService userService;
    private final ProviderService providerService;


    @PostMapping()
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<Map<String, String>> addNewUser(@Valid @RequestBody CreateServicePostDTO createServicePostDTO,
                                                          @AuthenticationPrincipal CustomUserDetails user) {
        
        Provider provider = providerService.getProviderByUser(userService.getUserById(user.getId()));

        servicePostService.createServicePost(createServicePostDTO, provider);

        Map<String, String> message = new HashMap<>();
        message.put("mensaje", "Service Post creado exitosamente");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
