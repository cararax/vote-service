package com.carara.vote.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2(topic = "CustomErrorDecoder")
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        switch (s) {
            case "AssociateClient#checkIfAssociateExists(Long)" -> {
                log.error("External request error: Associate not found");
                return new EntityNotFoundException("Associate not found");
            }
            case "AgendaClient#checkIfAgendaExists(Long)" -> {
                log.error("External request error: Agenda not found");
                return new EntityNotFoundException("Agenda not found");
            }
            default -> {
                log.error("External request error: " + response.reason());
                return new Exception();
            }
        }
    }
}
