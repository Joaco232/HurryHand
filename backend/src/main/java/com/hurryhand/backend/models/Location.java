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
    @NotNull(message = "El departamente no puede ser nulo")
    @Column(name = "DEPARTMENT", nullable = false)
    private DepartamentoUY departamento;

    @NotNull(message = "El departamente no puede ser nulo")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s'\\-]*$", message = "La ciudad solo puede contener letras y espacios.")
    @Column(name = "CITY", nullable = false, length = 80)
    private String city;

    @NotNull(message = "La calle principal no puede ser nula")
    @Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚüÜñÑ\\s'\\-\\.]*$", message = "La calle puede contener letras, números y guiones.")
    @Column(name = "STREET")
    private String street;

    @NotNull(message = "El numero de puerta no puede ser nulo")
    @Column(name = "STREET_NUMBER") //HAY QUE CHEQUEAR QUE NO SEA NEGATIVO NO ME DEJA USAR FUNCION MIN
    private Integer streetNumber;

    @NotNull(message = "El codigo postal no puede ser nulo")
    @Column(name = "POSTAL_CODE") //LO MISMO ACA CON QUE NO PUEDE HABER NEGATIVOS
    private Integer postalCode;

    @Column(name = "APTO_NUMBER")
    private Integer aptoNumber;

}
