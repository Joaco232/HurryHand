package com.hurryhand.backend.dto.servicepost;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicePostDTO {

    private String title;

    private String description;

    private BigDecimal rating;

    private Integer price;

    private Integer duration;

    private LocalDateTime createdAt;

    private List<LocalDateTime> availableDates;

    private List<String> photosURLs;

    private Long providerId;







}
