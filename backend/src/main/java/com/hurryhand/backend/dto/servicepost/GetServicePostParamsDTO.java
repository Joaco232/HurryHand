package com.hurryhand.backend.dto.servicepost;


import com.hurryhand.backend.enums.SortingDirection;
import com.hurryhand.backend.enums.SortingServicePosts;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetServicePostParamsDTO {

    @NotNull(message = "El número de página no puede ser null.")
    @Positive(message = "La página debe ser positiva.")
    private int page;

    @NotNull(message = "El tamaño de página no puede ser null.")
    @Positive(message = "El tamaño de página debe ser positivo.")
    private int size;

    @NotNull(message = "Se debe indicar el atributo por el cual se ordena.")
    private SortingServicePosts sortBy;

    @NotNull(message = "Se debe indicar la dirección en la que se ordena.")
    private SortingDirection direction;

    private String query;



}
