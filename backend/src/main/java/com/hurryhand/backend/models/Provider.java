package com.hurryhand.backend.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;


@Table(name = "PROVIDERS")
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Provider {

    @Id
    @Column(name = "USER_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "RATING", precision = 2, scale = 1)
    @DecimalMin(value = "0.0", inclusive = true, message = "El rating no puede ser menor a 0.0")
    @DecimalMax(value = "5.0", inclusive = true, message = "El rating no puede ser mayor a 5.0")
    private BigDecimal rating;

}
