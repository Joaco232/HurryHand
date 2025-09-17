package com.hurryhand.backend.mappers;


import com.hurryhand.backend.dto.provider.ProviderForInnerResponseDTO;
import com.hurryhand.backend.exceptions.attribute.NullValueForMapperException;
import com.hurryhand.backend.models.Provider;
import org.springframework.stereotype.Component;

@Component
public class ProviderMapper {

    public ProviderForInnerResponseDTO toProviderResponseDTO(Provider provider) {

        if (provider == null) {
            throw new NullValueForMapperException("Provider es null.");

        }

        return ProviderForInnerResponseDTO.builder()
                .rating(provider.getRating())
                .build();
    }

}
