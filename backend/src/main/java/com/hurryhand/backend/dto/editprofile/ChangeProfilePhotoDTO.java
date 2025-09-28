package com.hurryhand.backend.dto.editprofile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangeProfilePhotoDTO {

    @NotNull(message = "La imagen no puede vacia")
    @Size(max = 254, message = "La ruta de la foto de perfil no puede superar 254 caracteres.")
    private String profilePhoto;

}
