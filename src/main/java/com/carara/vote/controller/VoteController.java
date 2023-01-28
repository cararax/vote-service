package com.carara.vote.controller;

import com.carara.vote.domain.request.VoteRequest;
import com.carara.vote.service.VoteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/votes")
@AllArgsConstructor
public class VoteController {

    VoteService voteService;

    @PostMapping
    public void createVote(@Valid @RequestBody VoteRequest voteRequest) {
        voteService.createVote(voteRequest);
    }

}
