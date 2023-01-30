package com.carara.vote.service;


import com.carara.vote.domain.entity.Vote;
import com.carara.vote.domain.request.VoteRequest;
import com.carara.vote.exception.AssociateAlreadyVotedException;
import com.carara.vote.exception.VotingTimeExpiredException;
import com.carara.vote.infra.client.AgendaClient;
import com.carara.vote.infra.client.AssociateClient;
import com.carara.vote.infra.client.response.AgendaResponse;
import com.carara.vote.infra.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class VoteService {

    VoteRepository voteRepository;
    AssociateClient associateClient;
    AgendaClient agendaClient;

    public Vote createVote(VoteRequest voteRequest) throws VotingTimeExpiredException, AssociateAlreadyVotedException {
//        boolean existsByAssociateId = voteRepository.existsByAssociateId(voteRequest.getAssociateId());
//        if (existsByAssociateId) {
//            throw new AssociateAlreadyVotedException();
//        }

        ResponseEntity<AgendaResponse> agendaResponse = agendaClient.checkIfAgendaExists(voteRequest.getAgendaId());
//        if (Objects.requireNonNull(agendaResponse.getBody()).endDate().isBefore(LocalDateTime.now())) {
//            throw new VotingTimeExpiredException();
//        }

        associateClient.checkIfAssociateExists(voteRequest.getAssociateId());

        Vote voteEntity = new Vote();
        BeanUtils.copyProperties(voteRequest, voteEntity);
        voteEntity.setVoteTime(LocalDateTime.now());

        //todo: send request for email confirmation

        return voteRepository.save(voteEntity);
    }

    public List<Vote> getVotesByAgendaId(Long agendaId) {
        return voteRepository.findByAgendaId(agendaId);
    }
}
