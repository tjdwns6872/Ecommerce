package com.simple.ecommerce.serviceimpl.reviews;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.simple.ecommerce.config.RabbitMQProperties;
import com.simple.ecommerce.dto.products.ProductInsertDto;
import com.simple.ecommerce.dto.reviews.ReviewsInsertDto;
import com.simple.ecommerce.exception.products.ProductsException;
import com.simple.ecommerce.service.products.ProductsService;
import com.simple.ecommerce.service.reviews.ReviewsService;
import com.simple.ecommerce.util.RoutingKey;

@Service
public class ReviewsConsumer {

    private final RabbitMQProperties rabbitMQProperties;

    private final ReviewsService reviewsService;

    public ReviewsConsumer(RabbitMQProperties rabbitMQProperties
                            , ReviewsService reviewsService) {
        this.rabbitMQProperties = rabbitMQProperties;
        this.reviewsService = reviewsService;
    }    

    @RabbitListener(queues = "#{rabbitMQProperties.getQueues().getReviewInsert()}")
    @SendTo
    public Integer ProductsInsert(ReviewsInsertDto dto) {
        Integer reviewsId = 0;
        reviewsId = reviewsService.dataInsert(dto);
        return reviewsId;
    }
}
