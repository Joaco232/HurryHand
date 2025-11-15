package com.hurryhand.backend.dto.review;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewForInfoDTO {

    private Long id;
    private String title;
    private String body;
    private Double rating;
    private String userName;
    private String userProfilePhotoURL;

}
