package com.carara.vote.service;


import com.carara.vote.domain.entity.Vote;
import com.carara.vote.domain.request.VoteRequest;
import com.carara.vote.infra.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class VoteService {

    VoteRepository voteRepository;

    public void createVote(VoteRequest voteRequest) {

        //todo: check if agenda exists
        //todo: check if user exists
        //todo: check if user already voted

        Vote voteEntity = new Vote();
        BeanUtils.copyProperties(voteRequest, voteEntity);
        voteEntity.setVoteTime(LocalDateTime.now());

        voteRepository.save(voteEntity);

        //todo: send request for email confirmation
    }
}
