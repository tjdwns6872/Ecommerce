package com.simple.ecommerce.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
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
        private String productDelete;

        private String reviewInsert;
    }

    @Data
    public static class RoutingKeys {
        private String productInsert;
        private String productUpdate;
        private String productDelete;

        private String reviewInsert;
    }

    @Data
    public static class Exchange {
        private String productInsert;
        private String productUpdate;
        private String productDelete;

        private String reviewInsert;
    }
    
}
