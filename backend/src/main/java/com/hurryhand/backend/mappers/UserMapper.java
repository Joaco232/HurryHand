package com.hurryhand.backend.mappers;


import com.hurryhand.backend.dto.user.CreateUserDTO;
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
                .profilePhoto(dto.getProfilePhoto())
                .password(encodedPassword)
                .personalIdType(dto.getPersonalIdType())
                .personalId(dto.getPersonalId())
                .location(dto.getLocation())
                .build();
    }

}
