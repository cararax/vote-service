package com.carara.vote.infra.message;

import com.carara.vote.domain.entity.Vote;
import com.carara.vote.service.VoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpIllegalStateException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class VotePublisher {

    @Autowired
    private VoteService voteService;
    ObjectMapper newObjectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    //SERVER
    @RabbitListener(queues = "${rabbitmq.vote.queue}")
    public String publish(Long agendaId) throws JsonProcessingException {
        log.info(" [4] Received request from result to get votes for agenda" + agendaId);

        List<Vote> votesByAgendaId = voteService.getVotesByAgendaId(agendaId);
        if (votesByAgendaId.isEmpty()) {
            throw new AmqpIllegalStateException("Agenda without votes, try again later.");
        }
        String json = newObjectMapper.writeValueAsString(votesByAgendaId);

        log.info(" [5] Returned votes from agenda "+ agendaId+ " to result, data: " + json);
        return json;
    }
}
