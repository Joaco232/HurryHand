package com.hurryhand.backend.mappers;

import com.hurryhand.backend.dto.servicepost.CreateServicePostDTO;
import com.hurryhand.backend.dto.servicepost.ServicePostForVisualDTO;
import com.hurryhand.backend.exceptions.attribute.NullValueForMapperException;
import com.hurryhand.backend.models.Location;
import com.hurryhand.backend.models.Provider;
import com.hurryhand.backend.models.ServicePost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class ServicePostMapper {

    public ServicePost toEntity(CreateServicePostDTO createServicePostDTO, Provider provider) {

        if (createServicePostDTO == null || provider == null) {
            throw new NullValueForMapperException("Dto o provider es null.");
        }

        ServicePost.ServicePostBuilder<?, ?> builder = ServicePost.builder()
                .title(createServicePostDTO.getTitle())
                .description(createServicePostDTO.getDescription())
                .provider(provider)
                .price(createServicePostDTO.getPrice())
                .duration(Duration.ofMinutes(createServicePostDTO.getDurationInMinutes()))
                .availableDates(new ArrayList<>(createServicePostDTO.getAvailableDates()))
                .photosURLs(new ArrayList<>(createServicePostDTO.getPhotosURLs()));

        if (createServicePostDTO.getLocation() != null) {
            builder.location(mapLocation(createServicePostDTO.getLocation()));
        }

        return builder.build();
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

    private Location mapLocation(CreateServicePostDTO.LocationPayload locationPayload) {
        return Location.builder()
                .departamento(locationPayload.getDepartamento())
                .neighbourhood(locationPayload.getNeighbourhood())
                .street(locationPayload.getStreet())
                .streetNumber(locationPayload.getStreetNumber())
                .postalCode(locationPayload.getPostalCode())
                .aptoNumber(locationPayload.getAptoNumber())
                .build();
    }
}
