package com.simple.ecommerce.serviceimpl.products;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.simple.ecommerce.config.RabbitMQProperties;
import com.simple.ecommerce.util.products.ProductsRoutingKey;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductsProducer {
    
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQProperties rabbitMQProperties;

    public ProductsProducer(RabbitTemplate rabbitTemplate, RabbitMQProperties rabbitMQProperties){
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMQProperties = rabbitMQProperties;
    }

    public Integer sendMessage(Object dto, ProductsRoutingKey type){
        String routingKey = null;
        switch (type) {
            case INSERT:
                routingKey = rabbitMQProperties.getRoutingKeys().getProductInsert();
                break;
            case UPDATE:
                routingKey = rabbitMQProperties.getRoutingKeys().getProductUpdate();
                break;
            case DELETE:
                routingKey = rabbitMQProperties.getRoutingKeys().getProductDelete();
                break;
            default:
                throw new RuntimeException("No Consumer Type");
        }
        Object response = rabbitTemplate.convertSendAndReceive(
                rabbitMQProperties.getExchange().getTopic()
                , routingKey
                , dto
        );
        if (response != null) {
            return Integer.valueOf(response.toString());
        } else {
            throw new RuntimeException("Failed to receive response from Consumer");
        }

    }
}
