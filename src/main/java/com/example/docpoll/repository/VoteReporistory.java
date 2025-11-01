package com.example.docpoll.repository;

import com.example.docpoll.domain.Poll;
import com.example.docpoll.domain.Vote;
import com.example.docpoll.domain.VoteOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VoteReporistory extends JpaRepository<Vote, UUID> {
    long countByPollAndChosenOption(Poll poll, VoteOption voteOption);
}
