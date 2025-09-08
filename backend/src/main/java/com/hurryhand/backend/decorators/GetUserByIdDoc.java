package com.hurryhand.backend.decorators;


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
public @interface GetUserByIdDoc {
}
