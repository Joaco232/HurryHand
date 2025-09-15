package com.hurryhand.backend.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hurryhand.backend.dto.provider.ProviderResponseDTO;
import com.hurryhand.backend.enums.PersonalIdType;
import com.hurryhand.backend.models.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long id;

    private String email;

    private String name;

    private String surname;

    private LocalDate birthdate;

    private String profilePhoto;

    private String personalId;

    private PersonalIdType personalIdType;

    private Location location;

    private String phoneNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    private ProviderResponseDTO provider;


}
