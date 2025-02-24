package com.simple.ecommerce.serviceimpl.reviews;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.simple.ecommerce.config.RabbitMQProperties;
import com.simple.ecommerce.exception.reviews.ReviewsException;
import com.simple.ecommerce.util.RoutingKey;

@Service
public class ReviewsProducer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQProperties rabbitMQProperties;

    public ReviewsProducer(RabbitTemplate rabbitTemplate, RabbitMQProperties rabbitMQProperties){
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMQProperties = rabbitMQProperties;
    }

    public Integer sendMessage(Object dto, RoutingKey type){
        String routingKey = null;
        String topic = null;
        switch (type) {
            case INSERT:
                routingKey = rabbitMQProperties.getRoutingKeys().getReviewInsert();
                topic = rabbitMQProperties.getExchange().getReviewInsert();
                break;
            case UPDATE:
                break;
            case DELETE:
                break;
            default:
                throw new ReviewsException("No Consumer Type");
        }
        Object response = rabbitTemplate.convertSendAndReceive(
                topic
                , routingKey
                , dto
        );
        if (response != null) {
            return Integer.valueOf(response.toString());
        } else {
            throw new ReviewsException("Failed to receive response from Consumer");
        }

    }

}
