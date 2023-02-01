package com.carara.vote.infra.client.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public record AgendaResponse(String description,
                             @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
                             LocalDateTime endDate) implements Serializable {
}