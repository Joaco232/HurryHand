package com.hurryhand.backend.mappers;


import com.hurryhand.backend.dto.user.CreateUserDTO;
import com.hurryhand.backend.dto.user.UserResponseDTO;
import com.hurryhand.backend.exceptions.attribute.NullValueForMapperException;
import com.hurryhand.backend.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ProviderMapper providerMapper;

    public User toEntity(CreateUserDTO dto, String encodedPassword) {

        if (dto == null || encodedPassword == null) {
            throw new NullValueForMapperException("Dto o Password es null");

        }

        return User.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .surname(dto.getSurname())
                .birthdate(dto.getBirthdate())
                .password(encodedPassword)
                .personalIdType(dto.getPersonalIdType())
                .personalId(dto.getPersonalId())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public UserResponseDTO toUserResponseDTO(User user) {

        if (user == null) {
            throw new NullValueForMapperException("User es null.");

        }

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
                .phoneNumber(user.getPhoneNumber())
                .provider(user.getProvider() != null ? providerMapper.toProviderResponseDTO(user.getProvider()): null)
                .build();
    }

}
