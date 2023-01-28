package com.carara.vote.exception;

public class VotingTimeExpiredException extends RuntimeException {
    public VotingTimeExpiredException() {
        super("Voting time expired");
    }
}
