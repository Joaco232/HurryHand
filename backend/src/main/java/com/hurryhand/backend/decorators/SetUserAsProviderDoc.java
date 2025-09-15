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
import java.util.Map;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Set user como provider",
        description = "El usuario en sesión (requerida) es convertido en provider.")
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Usuario convertido en provider",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = Map.class)
                )),
        @ApiResponse(
                responseCode = "409",
                description = "Usuario ya es provider",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ApiError.class)
                ))
})
public @interface SetUserAsProviderDoc {
}
