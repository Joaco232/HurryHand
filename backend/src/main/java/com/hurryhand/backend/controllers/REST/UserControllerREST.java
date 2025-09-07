package com.hurryhand.backend.controllers.REST;


import com.hurryhand.backend.dto.ApiError;
import com.hurryhand.backend.dto.user.CreateUserDTO;
import com.hurryhand.backend.dto.user.UserResponseDTO;
import com.hurryhand.backend.mappers.UserMapper;
import com.hurryhand.backend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserControllerREST {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping()
    @Operation(summary = "Registrar Usuario",
            description = "Recibe los datos necesarios para registrar un nuevo usuario en la base de datos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "CreateUserDTO",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CreateUserDTO.class, name = "CreateUserDTO"))
    ))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario registrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Map.class)
                    )),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email ya registrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)
                    )),
            @ApiResponse(
                    responseCode = "406",
                    description = "Usuario menor de edad",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)
                    ))
    })
    public ResponseEntity<Map<String, String>> addNewUser(@Valid @RequestBody CreateUserDTO newUserDTO) {

        userService.addNewUser(newUserDTO);

        Map<String, String> message = new HashMap<>();
        message.put("mensaje", "Usuario registrado exitosamente");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID",
            description = "Devuelve los datos del usuario al ingresar su id.",
            parameters = {
                    @Parameter(name = "id", description = "ID del usuario", example = "123")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDTO.class)
                    )),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)
                    ))
    })
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {

        UserResponseDTO userDTO = userMapper.toUserResponseDTO(userService.getUserById(id));

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }





}
