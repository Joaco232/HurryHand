package com.hurryhand.backend.mappers;

import com.hurryhand.backend.dto.user.ProfilePhotoResponseDTO;
import com.hurryhand.backend.models.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component

public class ProfilePhotoMapper {

    public ProfilePhotoResponseDTO photoResponse(User user) {

        return ProfilePhotoResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .email(user.getEmail())
                .profilePhotoURL(user.getProfilePhoto())
                .build();
    }
}
