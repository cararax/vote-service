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
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Log4j2(topic = "VoteService")
public class VoteService {

    VoteRepository voteRepository;
    AssociateClient associateClient;
    AgendaClient agendaClient;

    public Vote createVote(VoteRequest voteRequest) throws VotingTimeExpiredException, AssociateAlreadyVotedException {
        log.info("Validatig vote for agenda id: " + voteRequest.getAgendaId() + " and associate id: "
                + voteRequest.getAssociateId());
        validateVote(voteRequest);
        log.info("Vote request sucessfully validated");

        Vote voteEntity = new Vote();
        BeanUtils.copyProperties(voteRequest, voteEntity);
        voteEntity.setVoteTime(LocalDateTime.now());
        return voteRepository.save(voteEntity);
    }

    private void validateVote(VoteRequest voteRequest) {
        checkIfUserAlreadyVoted(voteRequest);
        checkIfAgendaExistAndIsNotExpired(voteRequest);
        checkIfAssociateExists(voteRequest);
    }

    private void checkIfUserAlreadyVoted(VoteRequest voteRequest) {
        boolean existsByAssociateId = voteRepository.existsByAssociateId(voteRequest.getAssociateId());
        if (existsByAssociateId) {
            log.error("Associate id: " + voteRequest.getAssociateId() + " already voted");
            throw new AssociateAlreadyVotedException();
        }
    }

    private void checkIfAgendaExistAndIsNotExpired(VoteRequest voteRequest) {
        ResponseEntity<AgendaResponse> agendaResponse = agendaClient.checkIfAgendaExists(voteRequest.getAgendaId());
        if (Objects.requireNonNull(agendaResponse.getBody()).endDate().isBefore(LocalDateTime.now())) {
            log.error("Agenda id: " + voteRequest.getAgendaId() + " expired at " + agendaResponse.getBody().endDate());
            throw new VotingTimeExpiredException();
        }
    }

    private void checkIfAssociateExists(VoteRequest voteRequest) {
        associateClient.checkIfAssociateExists(voteRequest.getAssociateId());
    }

    public List<Vote> getVotesByAgendaId(Long agendaId) {
        return voteRepository.findByAgendaId(agendaId);
    }
}
