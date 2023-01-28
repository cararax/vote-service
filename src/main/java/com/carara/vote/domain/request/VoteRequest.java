package com.carara.vote.domain.request;

import com.carara.vote.domain.entity.VoteOption;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serializable;

@Data
public class VoteRequest implements Serializable {
    @Positive(message = "Associate ID invalid")
    @NotNull(message = "Associate ID is required")
    private final Long associateId;
    @Positive(message = "Agenda ID invalid")
    @NotNull(message = "Agenda ID is required")
    private final Long agendaId;
    @NotNull(message = "Vote option is required")
    private final VoteOption voteOption;
}