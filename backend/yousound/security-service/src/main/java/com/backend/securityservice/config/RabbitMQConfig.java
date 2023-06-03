package com.backend.securityservice.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1"); // Replace with your RabbitMQ server host
        factory.setPort(5672);
        factory.setUsername("guest"); // Replace with your RabbitMQ username
        factory.setPassword("guest"); // Replace with your RabbitMQ password
        return factory;
    }

    // RabbitMQ channel bean
    @Bean
    public RabbitTemplate rabbitTemplate(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory) {
        // Set any other necessary configurations for the RabbitTemplate
        return new RabbitTemplate(connectionFactory);
    }
}
