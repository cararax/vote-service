package com.carara.vote.infra.repository;

import com.carara.vote.domain.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByAssociateId(Long associateId);

    List<Vote> findByAgendaId(Long agendaId);

}