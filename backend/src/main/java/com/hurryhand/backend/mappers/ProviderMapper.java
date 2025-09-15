package com.hurryhand.backend.mappers;


import com.hurryhand.backend.dto.provider.ProviderResponseDTO;
import com.hurryhand.backend.dto.user.UserResponseDTO;
import com.hurryhand.backend.models.Provider;
import jdk.jfr.Category;
import org.springframework.stereotype.Component;

@Component
public class ProviderMapper {

    public ProviderResponseDTO toProviderResponseDTO(Provider provider) {

        return ProviderResponseDTO.builder()
                .rating(provider.getRating())
                .build();
    }

}
