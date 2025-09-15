package com.hurryhand.backend.mappers;

import com.hurryhand.backend.dto.servicepost.CreateServicePostDTO;
import com.hurryhand.backend.models.Provider;
import com.hurryhand.backend.models.ServicePost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServicePostMapper {

    public ServicePost toEntity(CreateServicePostDTO createServicePostDTO, Provider provider) {

        return ServicePost.builder()
                .title(createServicePostDTO.getTitle())
                .description(createServicePostDTO.getDescription())
                .provider(provider)
                .price(createServicePostDTO.getPrice())
                .location(createServicePostDTO.getLocation())
                .availableDates(createServicePostDTO.getAvailableDates())
                .photosURLs(createServicePostDTO.getPhotosURLs())
                .build();
    }




}
