package com.hurryhand.backend.mappers;

import com.hurryhand.backend.dto.servicepost.CreateServicePostDTO;
import com.hurryhand.backend.dto.servicepost.ServicePostDTO;
import com.hurryhand.backend.dto.servicepost.ServicePostForVisualDTO;
import com.hurryhand.backend.exceptions.attribute.NullValueForMapperException;
import com.hurryhand.backend.exceptions.servicepost.ServicePostNotFoundException;
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
            throw new ServicePostNotFoundException("Dto o provider es null."); //cambiar prueba algo explotaba
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
                .id(servicePost.getId())
                .title(servicePost.getTitle())
                .price(servicePost.getPrice())
                .photosURLs(servicePost.getPhotosURLs())
                .duration(servicePost.getDuration())
                .rating(servicePost.getRating())
                .build();
    }

    public ServicePostDTO toDto(ServicePost servicePost) {

        if (servicePost == null) {
            throw new NullValueForMapperException("Service Post es null.");

        }

        return ServicePostDTO.builder()
                .title(servicePost.getTitle())
                .description(servicePost.getDescription())
                .rating(servicePost.getRating())
                .price(servicePost.getPrice())
                .duration(servicePost.getDuration())
                .createdAt(servicePost.getCreatedAt())
                .availableDates(servicePost.getAvailableDates())
                .photosURLs(servicePost.getPhotosURLs())
                .build();

    }




}
