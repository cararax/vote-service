package com.carara.vote.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "associateId", nullable = false)
    private Long associateId;

    @Column(name = "agendaId", nullable = false)
    private Long agendaId;

    @Column(name = "voteOption", nullable = false)
    private VoteOption voteOption;

    @Column(name = "voteTime", nullable = false)
    private LocalDateTime voteTime;


}
