package com.simple.ecommerce.serviceimpl.products;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.simple.ecommerce.config.RabbitMQProperties;
import com.simple.ecommerce.config.RabbitMqConfig;

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

    public Integer sendMessage(Object message){
        Object response = rabbitTemplate.convertSendAndReceive(
                RabbitMqConfig.EXCHANGE_TOPIC
                , rabbitMQProperties.getRoutingKeys().getProductInsert()
                , message
        );
        if (response != null) {
            return Integer.valueOf(response.toString());
        } else {
            throw new RuntimeException("Failed to receive response from Consumer");
        }

    }
}
