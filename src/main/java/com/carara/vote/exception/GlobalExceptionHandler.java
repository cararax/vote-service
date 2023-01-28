package com.carara.vote.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler({AssociateNotFoundException.class, AgendaNotFoundException.class})
    public ResponseEntity<String> handleEntityNotFound(RuntimeException e) {
    //TODO: talvez nao retornar 404
        return ResponseEntity.status(404).body(e.getMessage());
    }

//    @ExceptionHandler({AssociateAlreadyVotedException.class})
//    public String handleAlreadyVoted() {
//        return "error/409";
//    }


}
