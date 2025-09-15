package com.hurryhand.backend.controllers.REST;


import com.hurryhand.backend.decorators.SetUserAsProviderDoc;
import com.hurryhand.backend.models.User;
import com.hurryhand.backend.security.CustomUserDetails;
import com.hurryhand.backend.services.ProviderService;
import com.hurryhand.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/provider")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class ProviderControllerREST {

    private final ProviderService providerService;
    private final UserService userService;

    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    @SetUserAsProviderDoc
    public ResponseEntity<Map<String, String>> setUserAsProvider(@AuthenticationPrincipal CustomUserDetails user) {

        User userToSet = userService.getUserById(user.getId());

        providerService.setUserAsProvider(userToSet);


        Map<String, String> message = new HashMap<>();
        message.put("mensaje", "Se ha registrado el usuario como provider exitosamente");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }








}
