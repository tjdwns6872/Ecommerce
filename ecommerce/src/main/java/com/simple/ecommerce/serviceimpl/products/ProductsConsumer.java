package com.simple.ecommerce.serviceimpl.products;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.simple.ecommerce.config.RabbitMQProperties;
import com.simple.ecommerce.dto.products.ProductDeleteDto;
import com.simple.ecommerce.dto.products.ProductInsertDto;
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

    @RabbitListener(queues = "#{rabbitMQProperties.getQueues().getProductInsert()}")
    @SendTo
    public Integer ProductsInsert(ProductInsertDto dto) {
        Integer productId = 0;
        try {
            productId = productsService.dataInsert(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productId;
    }

    @RabbitListener(queues = "#{rabbitMQProperties.getQueues().getProductUpdate()}")
    @SendTo
    public Integer ProductsUpdate(ProductInsertDto dto) {
        Integer productId = 0;
        try {
            productId = productsService.dataUpdate(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productId;
    }

    @RabbitListener(queues = "#{rabbitMQProperties.getQueues().getProductDelete()}")
    @SendTo
    public Integer ProductsDelete(ProductDeleteDto dto) {
        Integer productId = 0;
        try {
            productId = productsService.dataDelete(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productId;
    }

}
