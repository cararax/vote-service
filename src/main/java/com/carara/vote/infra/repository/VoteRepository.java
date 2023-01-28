package com.carara.vote.infra.repository;

import com.carara.vote.domain.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByAssociateId(Long associateId);
}