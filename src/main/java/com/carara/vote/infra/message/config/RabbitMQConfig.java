package com.carara.vote.infra.message.config;

import com.carara.vote.infra.message.VotePublisher;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //CLIENT
    @Value("${rabbitmq.exchange}")
    public String exchangeName;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public VotePublisher client() {
        return new VotePublisher();
    }
}
