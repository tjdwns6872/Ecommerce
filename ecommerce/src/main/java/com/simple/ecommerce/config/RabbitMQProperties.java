package com.simple.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMQProperties {

    private Queues queues;
    private RoutingKeys routingKeys;
    private Exchange exchange;

    @Data
    public static class Queues {
        private String productInsert;
        private String productUpdate;
    }

    @Data
    public static class RoutingKeys {
        private String productInsert;
        private String productUpdate;
    }

    @Data
    public static class Exchange {
        private String topic;
    }
    
}
