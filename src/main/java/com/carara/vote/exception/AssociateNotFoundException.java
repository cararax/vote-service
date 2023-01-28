package com.carara.vote.exception;

public class AssociateNotFoundException extends RuntimeException{
    public AssociateNotFoundException() {
        super("Associate not found");
    }
}
