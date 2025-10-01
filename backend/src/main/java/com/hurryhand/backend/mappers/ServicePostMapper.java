package com.hurryhand.backend.mappers;

import com.hurryhand.backend.dto.servicepost.CreateServicePostDTO;
import com.hurryhand.backend.dto.servicepost.ServicePostForVisualDTO;
import com.hurryhand.backend.exceptions.attribute.NullValueForMapperException;
import com.hurryhand.backend.models.Provider;
import com.hurryhand.backend.models.ServicePost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServicePostMapper {

    private final LocationMapper locationMapper;

    public ServicePost toEntity(CreateServicePostDTO createServicePostDTO, Provider provider) {

        if (createServicePostDTO == null || provider == null) {
            throw new NullValueForMapperException("Dto o provider es null.");

        }

        return ServicePost.builder()
                .title(createServicePostDTO.getTitle())
                .description(createServicePostDTO.getDescription())
                .provider(provider)
                .price(createServicePostDTO.getPrice())
                .location(locationMapper.toEntity(createServicePostDTO.getLocation()))
                .availableDates(createServicePostDTO.getAvailableDates())
                .duration(createServicePostDTO.getDuration())
                .build();
    }


    public ServicePostForVisualDTO toServicePostForVisualDTO(ServicePost servicePost) {

        if (servicePost == null) {
            throw new NullValueForMapperException("Service Post es null.");

        }

        return ServicePostForVisualDTO.builder()
                .title(servicePost.getTitle())
                .price(servicePost.getPrice())
                .photosURLs(servicePost.getPhotosURLs())
                .duration(servicePost.getDuration())
                .rating(servicePost.getRating())
                .build();
    }




}
