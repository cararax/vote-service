package com.carara.vote.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler({AssociateNotFoundException.class, AgendaNotFoundException.class, VotingTimeExpiredException.class})
    public ResponseEntity<String> handleEntityNotFound(RuntimeException e) {
        return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({AssociateAlreadyVotedException.class})
    public ResponseEntity<String> handleAlreadyVoted(AssociateAlreadyVotedException e) {
        return ResponseEntity.status(CONFLICT).body(e.getMessage());
    }


}
