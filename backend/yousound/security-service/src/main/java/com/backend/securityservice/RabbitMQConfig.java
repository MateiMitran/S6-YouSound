package com.backend.securityservice;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@Configuration
public class RabbitMQConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("rabbitmq"); // Replace with your RabbitMQ server host
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


    @Bean
    public Queue myQueue() {
        return new Queue("user-service-queue", true);
    }

    @Bean
    public DirectExchange myExchange() {
        return new DirectExchange("create-artist");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(myQueue()).to(myExchange()).with("my-routing-key");
    }
}
