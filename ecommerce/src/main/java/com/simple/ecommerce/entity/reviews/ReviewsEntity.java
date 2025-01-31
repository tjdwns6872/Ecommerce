package com.simple.ecommerce.entity.reviews;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="EC_REVIEWS")
public class ReviewsEntity {
    
    @Id
    @Column(name = "EC_REVIEWS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ecReviewsId;

    @Column(name = "EC_REVIEWS_USER_ID")
    private Integer ecReviewsUserId;

    @Column(name = "EC_REVIEWS_PRODUCT_ID")
    private Integer ecReviewsProductId;

    @Column(name = "EC_REVIEWS_RATING")
    private Integer ecReviewsRating;

    @Column(name = "EC_REVIEWS_COMMENT")
    private String ecReviewsComment;

    @Column(name = "EC_REVIEWS_CREATED_AT")
    private Date ecReviewsCreatedAt;

    @Column(name = "EC_REVIEWS_UPDATED_AT")
    private Date ecReviewsUpdatedAt;
}
