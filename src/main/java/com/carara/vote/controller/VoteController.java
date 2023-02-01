package com.carara.vote.controller;

import com.carara.vote.domain.entity.Vote;
import com.carara.vote.domain.request.VoteRequest;
import com.carara.vote.exception.AssociateAlreadyVotedException;
import com.carara.vote.exception.VotingTimeExpiredException;
import com.carara.vote.infra.message.VotePublisher;
import com.carara.vote.service.VoteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votes")
@AllArgsConstructor
@Log4j2(topic = "VoteController")
public class VoteController {

    VoteService voteService;
    VotePublisher votePublisher;

    @PostMapping
    public ResponseEntity<Vote> createVote(@Valid @RequestBody VoteRequest voteRequest) throws AssociateAlreadyVotedException, VotingTimeExpiredException {
        Vote vote = voteService.createVote(voteRequest);
        log.info("Vote sucessfully created: " + vote.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(vote);
    }

}
