package com.hurryhand.backend.mappers;


import com.hurryhand.backend.dto.user.CreateUserDTO;
import com.hurryhand.backend.dto.user.UserResponseDTO;
import com.hurryhand.backend.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(CreateUserDTO dto, String encodedPassword) {

        return User.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .surname(dto.getSurname())
                .birthdate(dto.getBirthdate())
                .password(encodedPassword)
                .personalIdType(dto.getPersonalIdType())
                .personalId(dto.getPersonalId())
                .build();
    }

    public UserResponseDTO toUserResponseDTO(User user) {

        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .birthdate(user.getBirthdate())
                .profilePhoto(user.getProfilePhoto())
                .personalId(user.getPersonalId())
                .personalIdType(user.getPersonalIdType())
                .location(user.getLocation())
                .createdAt(user.getCreatedAt())
                .build();
    }

}
