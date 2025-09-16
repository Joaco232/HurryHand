package com.hurryhand.backend.dto.servicepost;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicePostForVisualDTO {

    private String title;

    private BigDecimal rating;

    private Integer price;

    private Duration duration;

    private List<String> photosURLs = new ArrayList<>();
}
