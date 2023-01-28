package com.carara.vote.exception;

public class AssociateAlreadyVotedException extends RuntimeException {
    public AssociateAlreadyVotedException() {
        super("Associate already voted");
    }
}
