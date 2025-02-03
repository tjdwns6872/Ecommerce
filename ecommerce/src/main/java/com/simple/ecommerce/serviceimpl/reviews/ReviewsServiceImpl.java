package com.simple.ecommerce.serviceimpl.reviews;

import org.springframework.stereotype.Service;

import com.simple.ecommerce.converter.reviews.ReviewsConverter;
import com.simple.ecommerce.dto.reviews.ReviewsInsertDto;
import com.simple.ecommerce.entity.reviews.ReviewsEntity;
import com.simple.ecommerce.exception.reviews.ReviewsException;
import com.simple.ecommerce.repository.reviews.ReviewsRepository;
import com.simple.ecommerce.service.reviews.ReviewsService;

@Service
public class ReviewsServiceImpl implements ReviewsService{

    private final ReviewsRepository reviewsRepository;

    public ReviewsServiceImpl(ReviewsRepository reviewsRepository){
        this.reviewsRepository = reviewsRepository;
    }

    @Override
    public Integer dataInsert(ReviewsInsertDto dto) {
        Integer reviewsId = 0;
        try {
            ReviewsEntity entity = new ReviewsConverter().toEntity(dto);
            reviewsId = reviewsRepository.save(entity).getEcReviewsId();
        } catch (Exception e) {
            new ReviewsException("저장 실패");
        }
        return reviewsId;
    }
    
}
