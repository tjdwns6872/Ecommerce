package com.simple.ecommerce.repository.reviews;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simple.ecommerce.entity.reviews.ReviewsEntity;

public interface ReviewsRepository extends JpaRepository<ReviewsEntity, Integer>{
    
}
