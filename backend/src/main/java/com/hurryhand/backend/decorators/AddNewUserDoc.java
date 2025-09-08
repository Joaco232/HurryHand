package com.hurryhand.backend.decorators;


import com.hurryhand.backend.auth.LoginRequest;
import com.hurryhand.backend.auth.LoginResponse;
import com.hurryhand.backend.dto.ApiError;
import com.hurryhand.backend.dto.user.CreateUserDTO;
import com.hurryhand.backend.dto.user.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
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
public @interface AddNewUserDoc {
}
