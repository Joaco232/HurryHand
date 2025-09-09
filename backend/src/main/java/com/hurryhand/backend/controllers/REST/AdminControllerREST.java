package com.hurryhand.backend.controllers.REST;

import com.hurryhand.backend.dto.admin.CreateAdminDTO;
import com.hurryhand.backend.dto.user.CreateUserDTO;
import com.hurryhand.backend.services.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminControllerREST {

    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<Map<String, String>> addNewUser(@Valid @RequestBody CreateAdminDTO newAdminDTO){

        adminService.addNewAdmin(newAdminDTO);
        Map<String, String> message = new HashMap<>();
        message.put("mensaje", "Admin creado exitosamente");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
