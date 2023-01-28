package com.carara.vote.config;

import com.carara.vote.exception.AgendaNotFoundException;
import com.carara.vote.exception.AssociateNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        switch (s) {
            case "AssociateClient#checkIfAssociateExists(Long)":
                return new AssociateNotFoundException();
            case "AgendaClient#checkIfAgendaExists(Long)":
                return new AgendaNotFoundException();
            default:
                return new Exception();
        }
    }
}
