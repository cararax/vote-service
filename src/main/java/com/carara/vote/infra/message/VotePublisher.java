package com.carara.vote.infra.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VotePublisher {

    //CLIENT

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingKey;


    int start = 0;

    public void send() {
//        System.out.println(" [x] Requesting fib(" + start + ")");
        log.info(" [x] Requesting fib(" + start + ")");
        Integer response = (Integer) template.convertSendAndReceive(exchange.getName(), routingKey, start++);
//        System.out.println(" [.] Got '" + response + "'");
        log.info(" [.] Got '" + response + "'");
    }

}
