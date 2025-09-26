package com.hurryhand.backend.controllers.REST;

import com.hurryhand.backend.decorators.AddNewAdminDoc;
import com.hurryhand.backend.decorators.AddNewUserDoc;
import com.hurryhand.backend.dto.ApiResponse;
import com.hurryhand.backend.dto.admin.CreateAdminDTO;
import com.hurryhand.backend.dto.user.CreateUserDTO;
import com.hurryhand.backend.mappers.ApiResponseMapper;
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
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminControllerREST {

    private final AdminService adminService;
    private final ApiResponseMapper apiResponseMapper;

    @PostMapping()
    @AddNewAdminDoc
    public ResponseEntity<ApiResponse> addNewUser(@Valid @RequestBody CreateAdminDTO newAdminDTO){

        adminService.addNewAdmin(newAdminDTO);

        return apiResponseMapper.makeResponseEntity(HttpStatus.OK,"Admin creado exitosamente");
    }


}
