package com.hurryhand.backend.auth;


import com.hurryhand.backend.dto.ApiError;
import com.hurryhand.backend.dto.user.CreateUserDTO;
import com.hurryhand.backend.dto.user.UserResponseDTO;
import com.hurryhand.backend.mappers.UserMapper;
import com.hurryhand.backend.models.User;
import com.hurryhand.backend.security.CustomUserDetails;
import com.hurryhand.backend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping("/login")
    @Operation(summary = "Login general",
            description = "Login para usuarios comunes y administradores.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "LoginRequest",
                    required = true,
                    content = @Content(schema = @Schema(implementation = LoginRequest.class, name = "LoginRequest"))
            ))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Login exitoso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponse.class)
                    )),
            @ApiResponse(
                    responseCode = "403",
                    description = "Error de credenciales",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)
                    ))
    })
    public ResponseEntity<?> login(HttpServletRequest HttpRequest, @RequestBody LoginRequest loginRequest) {

        // 1. Crear el token de autenticación
        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        // 2. Capturar IP y session ID
        authRequest.setDetails(new WebAuthenticationDetails(HttpRequest));

        // 3. Ejecutar autenticación
        Authentication authentication = authenticationManager.authenticate(authRequest);

        // 4. Extraer detalles
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String ip = details.getRemoteAddress();
        String sessionId = details.getSessionId();

        // 5. Extraer usuario autenticado
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        // 6. Generar JWT
        String token = jwtService.generateToken(user);

        // 7. Devolver token + info útil
        return ResponseEntity.ok(new LoginResponse(token, user.getEmail(), ip));
    }


    @GetMapping("/me")
    public ResponseEntity<?> loggedUserInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        UserResponseDTO dto = userMapper.toUserResponseDTO(
                userService.getUserById(userDetails.getId())
        );

        return ResponseEntity.ok(dto);
    }





}