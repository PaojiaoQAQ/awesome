package com.example.demo.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {
    @Bean
    public Queue Queue() {
        return new Queue("queue");
    }
}
