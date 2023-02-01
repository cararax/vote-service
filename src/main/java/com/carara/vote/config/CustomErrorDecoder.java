package com.carara.vote.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.persistence.EntityNotFoundException;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        switch (s) {
            case "AssociateClient#checkIfAssociateExists(Long)":
                return new EntityNotFoundException("Associate not found");
            case "AgendaClient#checkIfAgendaExists(Long)":
                return new EntityNotFoundException("Agenda not found");
            default:
                return new Exception();
        }
    }
}
