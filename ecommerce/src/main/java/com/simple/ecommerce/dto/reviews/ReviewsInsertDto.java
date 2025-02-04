package com.simple.ecommerce.dto.reviews;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class ReviewsInsertDto {
    
    private String userToken;
    private Integer userId;

    private Integer productId;

    private Integer rating;

    private String comment;

    private Date created;
}
