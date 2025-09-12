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
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;


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

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ServicePost> servicePosts = new ArrayList<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Credential> credentials = new ArrayList<>();


    public void updateRating() {

        if (servicePosts.isEmpty()) {
            this.rating = null;
            return;
        }

        OptionalDouble OptAverageRating = this.servicePosts.stream()
                .map(servicePost -> servicePost.getRating())
                .filter(rating -> rating != null)
                .mapToDouble(rating -> rating.doubleValue())
                .average();

        this.rating = OptAverageRating.isPresent() ? BigDecimal.valueOf(OptAverageRating.getAsDouble()) : null;
    }

}
