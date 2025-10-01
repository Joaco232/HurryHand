package com.hurryhand.backend.mappers;


import com.hurryhand.backend.dto.LocationPayload;
import com.hurryhand.backend.models.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    public Location toEntity(LocationPayload location) {

        return Location.builder()
                .departamento(location.getDepartamento())
                .neighbourhood(location.getNeighbourhood())
                .street(location.getStreet())
                .streetNumber(location.getStreetNumber())
                .postalCode(location.getPostalCode())
                .aptoNumber(location.getAptoNumber())
                .build();
    }







}




