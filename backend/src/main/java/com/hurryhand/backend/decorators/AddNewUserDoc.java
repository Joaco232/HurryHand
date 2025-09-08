package com.hurryhand.backend.decorators;


import com.hurryhand.backend.auth.LoginRequest;
import com.hurryhand.backend.auth.LoginResponse;
import com.hurryhand.backend.dto.ApiError;
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


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
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
public @interface AddNewUserDoc {
}
