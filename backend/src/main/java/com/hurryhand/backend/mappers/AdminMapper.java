package com.hurryhand.backend.mappers;

import com.hurryhand.backend.dto.admin.CreateAdminDTO;
import com.hurryhand.backend.dto.user.CreateUserDTO;
import com.hurryhand.backend.exceptions.attribute.NullValueForMapperException;
import com.hurryhand.backend.models.Admin;
import com.hurryhand.backend.models.User;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {
    public Admin toEntity(CreateAdminDTO dto, String encodedPassword) {

        if (dto == null) {
            throw new NullValueForMapperException("Dto es null.");

        }

        return Admin.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .surname(dto.getSurname())
                .password(encodedPassword)
                .personalIdType(dto.getPersonalIdType())
                .personalId(dto.getPersonalId())
                .build();
    }

}
