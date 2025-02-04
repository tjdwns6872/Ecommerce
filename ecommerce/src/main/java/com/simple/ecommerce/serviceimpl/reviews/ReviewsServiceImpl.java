package com.simple.ecommerce.serviceimpl.reviews;

import org.springframework.stereotype.Service;

import com.simple.ecommerce.converter.reviews.ReviewsConverter;
import com.simple.ecommerce.dto.reviews.ReviewsInsertDto;
import com.simple.ecommerce.entity.reviews.ReviewsEntity;
import com.simple.ecommerce.exception.reviews.ReviewsException;
import com.simple.ecommerce.repository.reviews.ReviewsRepository;
import com.simple.ecommerce.service.jwt.JwtService;
import com.simple.ecommerce.service.reviews.ReviewsService;
import com.simple.ecommerce.util.JwtUtil;

@Service
public class ReviewsServiceImpl implements ReviewsService{

    private final ReviewsRepository reviewsRepository;

    private final JwtService jwtService;

    public ReviewsServiceImpl(ReviewsRepository reviewsRepository
                            , JwtService jwtService){
        this.reviewsRepository = reviewsRepository;
        this.jwtService = jwtService;
    }

    @Override
    public Integer dataInsert(ReviewsInsertDto dto) {
        Integer reviewsId = 0;
        try {
            dto.setUserId(jwtService.tokenToUserId(dto.getUserToken()));
            ReviewsEntity entity = new ReviewsConverter().toEntity(dto);
            reviewsId = reviewsRepository.save(entity).getEcReviewsId();
        } catch (Exception e) {
            new ReviewsException("저장 실패");
        }
        return reviewsId;
    }
    
}
