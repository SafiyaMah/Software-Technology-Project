package com.example.docpoll.patient.service;

import com.example.docpoll.domain.Poll;
import com.example.docpoll.domain.Vote;
import com.example.docpoll.domain.VoteOption;
import com.example.docpoll.patient.dto.PollPatientView;
import com.example.docpoll.repository.PollRepository;
import com.example.docpoll.repository.VoteOptionRepository;
import com.example.docpoll.repository.VoteReporistory;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PatientPollServiceImp implements PatientPollService {
        
        private final PollRepository pollRepository;
        private final VoteOptionRepository voteOptionRepository;
        private final VoteReporistory voteReporistory;

        @Override
        public List<PollPatientView> listPolls() {
                // TODO filter by public/private polls
                return pollRepository.findAll(Sort.by(Sort.Direction.DESC, "createdTime"))
                .stream()
                .map(poll -> {
                        var optionViews = poll.getVoteOptionList().stream()
                                .map(o -> new PollPatientView.OptionView(
                                        o.getVoteOptionId(),
                                        o.getCaption()
                                ))
                                .toList();

                        return new PollPatientView(
                                poll.getPollId(),
                                poll.getQuestion(),
                                poll.getCreatedTime(),
                                poll.isCompleted(),
                                optionViews
                        );
                })
                .toList();
        }

        @Override
        public PollPatientView castVote(UUID pollId, UUID voteOptionId) {
        // Get poll
        Poll poll = pollRepository.findById(pollId).orElseThrow(() -> new IllegalArgumentException("Poll not found"));
        // Get option
        VoteOption voteOption = voteOptionRepository.findById(voteOptionId).orElseThrow(() -> new IllegalArgumentException("Vote option not found"));

        if (voteOption.getPoll() == null || !voteOption.getPoll().getPollId().equals(pollId)) {
                throw new IllegalArgumentException("Vote option does not belong to this poll");
        }

        // TODO
        // - get current user from Keycloak token
        // - check this user hasn't already voted in this poll
        Vote vote = new Vote();
        vote.setPoll(poll);
        vote.setChosenOption(voteOption);
        // vote.setUser(currentUser); // when Keycloak is wired
        voteReporistory.save(vote);

        // Return PollPatientView for this poll (to mark completed=true for this patient)
        var optionViews = poll.getVoteOptionList().stream()
                .map(o -> new PollPatientView.OptionView(
                        o.getVoteOptionId(),
                        o.getCaption()
                ))
                .toList();

        return new PollPatientView(
                poll.getPollId(),
                poll.getQuestion(),
                poll.getCreatedTime(),
                poll.isCompleted(),
                optionViews
        );

        }

}
