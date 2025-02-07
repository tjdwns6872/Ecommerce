package com.simple.ecommerce.serviceimpl.products;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.simple.ecommerce.config.RabbitMQProperties;
import com.simple.ecommerce.service.products.ProductsService;

@Service
public class ProductsConsumer{

    private final RabbitMQProperties rabbitMQProperties;

    private final ProductsService productsService;

    public ProductsConsumer(RabbitMQProperties rabbitMQProperties
                            , ProductsService productsService) {
        this.rabbitMQProperties = rabbitMQProperties;
        this.productsService = productsService;
    }

    @RabbitListener(queues = "#{rabbitMQProperties.getQueues().getProductList()}")
    @SendTo
    public Integer ProductsList() {
        Integer productId = productsService.dataInsert(null);
        return productId;
    }

}
