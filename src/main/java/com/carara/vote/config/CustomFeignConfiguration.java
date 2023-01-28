package com.carara.vote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomFeignConfiguration {
    @Bean
    public CustomErrorDecoder customDecoder() {
        return new CustomErrorDecoder();
    }
}
