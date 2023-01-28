package com.carara.vote.exception;

public class AgendaNotFoundException extends RuntimeException{
    public AgendaNotFoundException() {
        super("Agenda not found");
    }
}
