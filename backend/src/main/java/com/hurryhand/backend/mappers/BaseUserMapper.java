package com.hurryhand.backend.mappers;

import com.hurryhand.backend.dto.UserDetailsDTO;
import com.hurryhand.backend.exceptions.attribute.NullValueForMapperException;
import com.hurryhand.backend.models.BaseUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BaseUserMapper {


    public UserDetailsDTO toUserDetailsDTO(BaseUser baseUser) {

        if (baseUser == null) {
            throw new NullValueForMapperException("Base User es null.");

        }

        return UserDetailsDTO.builder()
                .id(baseUser.getId())
                .password(baseUser.getPassword())
                .email(baseUser.getEmail())
                .roles(new ArrayList<>(baseUser.getRoles()))
                .build();






    }


}