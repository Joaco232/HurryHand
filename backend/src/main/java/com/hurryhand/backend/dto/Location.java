package com.hurryhand.backend.dto;


import com.hurryhand.backend.enums.DepartamentoUY;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Location {

    private DepartamentoUY departamento;
    private  String city;
    private  String street;
    private  Integer streetNumber;
    private  Integer postalCode;

}
