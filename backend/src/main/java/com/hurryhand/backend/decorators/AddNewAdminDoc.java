package com.hurryhand.backend.decorators;


import com.hurryhand.backend.auth.LoginRequest;
import com.hurryhand.backend.auth.LoginResponse;
import com.hurryhand.backend.dto.ApiError;
import com.hurryhand.backend.dto.admin.CreateAdminDTO;
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
@Operation(summary = "Registrar Admin",
        description = "Crea un nuevo usuario con rol ADMIN en el sistema" + "Este endpoint requiere de permsisos de administrador para su uso (ROLE_ADMIN)",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "CreateAdminDTO",
                required = true,
                content = @Content(schema = @Schema(implementation = CreateAdminDTO.class, name = "CreateAdminDTO"))
        ))
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Admin creado correctamente",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = Map.class)
                )),
        @ApiResponse(
                responseCode = "403", //No se porque pone aca el 403
                description = "Accesos denegado: el usuario autenticado no es un ADMIN",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ApiError.class)
                )
        ),
        @ApiResponse(
                responseCode = "409",
                description = "Email ya registrado",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ApiError.class)
                )),
})
public @interface AddNewAdminDoc {
}