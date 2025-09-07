package com.hurryhand.backend.models;


import com.hurryhand.backend.enums.DepartamentoUY;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Location {

    @Enumerated(EnumType.STRING)
    @Column(name = "DEPARTMENT")
    private DepartamentoUY departamento;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s'\\-]*$", message = "La ciudad solo puede contener letras y espacios.")
    @Column(name = "CITY", length = 80)
    private String city;

    @Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚüÜñÑ\\s'\\-\\.]*$", message = "La calle puede contener letras, números y guiones.")
    @Column(name = "STREET")
    private String street;

    @Column(name = "STREET_NUMBER") //HAY QUE CHEQUEAR QUE NO SEA NEGATIVO NO ME DEJA USAR FUNCION MIN
    private Integer streetNumber;

    @Column(name = "POSTAL_CODE") //LO MISMO ACA CON QUE NO PUEDE HABER NEGATIVOS
    private Integer postalCode;

    @Column(name = "APTO_NUMBER")
    private Integer aptoNumber;

}
