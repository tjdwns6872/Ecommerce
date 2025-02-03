package com.simple.ecommerce.converter.reviews;

import com.simple.ecommerce.converter.ToEntityConverter;
import com.simple.ecommerce.dto.reviews.ReviewsInsertDto;
import com.simple.ecommerce.entity.reviews.ReviewsEntity;

public class ReviewsConverter implements ToEntityConverter<ReviewsEntity, ReviewsInsertDto>{

    @Override
    public ReviewsEntity toEntity(ReviewsInsertDto dto) {
        ReviewsEntity entity = ReviewsEntity.builder()
            .ecReviewsUserId(dto.getUserId())
            .ecReviewsProductId(dto.getProductId())
            .ecReviewsRating(dto.getRating())
            .ecReviewsComment(dto.getComment())
            .ecReviewsCreatedAt(dto.getCreated())
            .ecReviewsUpdatedAt(dto.getCreated())
            .build();
        return entity;
    }
    
}
