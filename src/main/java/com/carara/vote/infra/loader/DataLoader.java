package com.carara.vote.infra.loader;

import com.carara.vote.domain.entity.Vote;
import com.carara.vote.domain.entity.VoteOption;
import com.carara.vote.infra.repository.VoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
@Log4j2(topic = "DataLoader")
public class DataLoader implements CommandLineRunner {

    VoteRepository voteRepository;

    @Override
    public void run(String... args) {
        if (voteRepository.count() == 0) {
            loadVoteData();
        }
    }

    public void loadVoteData() {
        List<Vote> voteList = List.of(
                new Vote(1L, 1L, VoteOption.YES, LocalDateTime.now()),
                new Vote(2L, 1L, VoteOption.YES, LocalDateTime.now()),

                new Vote(3L, 2L, VoteOption.NO, LocalDateTime.now()),
                new Vote(4L, 2L, VoteOption.NO, LocalDateTime.now()),
                new Vote(5L, 2L, VoteOption.NO, LocalDateTime.now()),
                new Vote(6L, 2L, VoteOption.YES, LocalDateTime.now()),

                new Vote(7L, 3L, VoteOption.YES, LocalDateTime.now()),
                new Vote(8L, 3L, VoteOption.YES, LocalDateTime.now()),
                new Vote(9L, 3L, VoteOption.YES, LocalDateTime.now()),
                new Vote(1L, 3L, VoteOption.NO, LocalDateTime.now()),
                new Vote(2L, 3L, VoteOption.NO, LocalDateTime.now()),
                new Vote(3L, 3L, VoteOption.NO, LocalDateTime.now()));
        voteRepository.saveAll(voteList);
        log.info("Vote loaded to database");
    }
}
