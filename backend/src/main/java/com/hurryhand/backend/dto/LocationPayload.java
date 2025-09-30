package com.hurryhand.backend.dto;

import com.hurryhand.backend.enums.DepartamentoUY;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationPayload {

    private DepartamentoUY departamento;
    private String neighbourhood;
    private String street;
    private Integer streetNumber;
    private Integer postalCode;
    private Integer aptoNumber;
}