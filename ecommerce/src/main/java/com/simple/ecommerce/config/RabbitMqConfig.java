package com.simple.ecommerce.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableRabbit
@Slf4j
public class RabbitMqConfig {

    private final RabbitMQProperties rabbitMQProperties;

    public RabbitMqConfig(RabbitMQProperties rabbitMQProperties) {
        this.rabbitMQProperties = rabbitMQProperties;
    }
    
    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private Integer port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;
    
    @Bean
    public Queue productInsert(){
        return new Queue(rabbitMQProperties.getQueues().getProductInsert(), true);
    }
    @Bean
    public Binding productInsertBinding(Queue productInsert, TopicExchange productInsertExchange){
        return BindingBuilder.bind(productInsert).to(productInsertExchange).with(rabbitMQProperties.getRoutingKeys().getProductInsert());
    }
    @Bean 
    public TopicExchange productInsertExchange(){
        return new TopicExchange(rabbitMQProperties.getExchange().getProductInsert());
    }

    @Bean
    public Queue productUpdate(){
        return new Queue(rabbitMQProperties.getQueues().getProductUpdate(), true);
    }
    @Bean
    public Binding productUpdateBinding(Queue productUpdate, TopicExchange productUpdateExchange){
        return BindingBuilder.bind(productUpdate).to(productUpdateExchange).with(rabbitMQProperties.getRoutingKeys().getProductUpdate());
    }
    @Bean 
    public TopicExchange productUpdateExchange(){
        return new TopicExchange(rabbitMQProperties.getExchange().getProductUpdate());
    }

    @Bean
    public Queue productDelete(){
        return new Queue(rabbitMQProperties.getQueues().getProductDelete(), true);
    }
    @Bean
    public Binding productDeleteBinding(Queue productDelete, TopicExchange productDeleteExchange){
        return BindingBuilder.bind(productDelete).to(productDeleteExchange).with(rabbitMQProperties.getRoutingKeys().getProductDelete());
    }
    @Bean 
    public TopicExchange productDeleteExchange(){
        return new TopicExchange(rabbitMQProperties.getExchange().getProductDelete());
    }

    @Bean
    public Queue reviewInsert(){
        return new Queue(rabbitMQProperties.getQueues().getReviewInsert(), true);
    }
    @Bean
    public Binding reviewInsertBinding(Queue reviewInsert, TopicExchange reviewInsertExchange){
        return BindingBuilder.bind(reviewInsert).to(reviewInsertExchange).with(rabbitMQProperties.getRoutingKeys().getReviewInsert());
    }
    @Bean 
    public TopicExchange reviewInsertExchange(){
        return new TopicExchange(rabbitMQProperties.getExchange().getReviewInsert());
    }


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

