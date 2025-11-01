package com.example.docpoll.admin.service;

import com.example.docpoll.admin.dto.InsightView;
import com.example.docpoll.admin.dto.PollAdminView;
import com.example.docpoll.domain.Poll;
import com.example.docpoll.repository.PollRepository;
import com.example.docpoll.repository.VoteReporistory;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminPollServiceImp implements AdminPollService {
    private final PollRepository pollRepository;
    private final VoteReporistory voteRepository;

    @Override
    public PollAdminView createPoll() {
        //TODO
        return null;
    }

    @Override
    public PollAdminView getPoll() {
        //TODO
        return null;
    }

    @Override
    public List<PollAdminView> listPolls() {
        return pollRepository.findAll(Sort.by(Sort.Direction.DESC, "createdTime"))
                .stream()
                .map(poll -> new PollAdminView(
                        poll.getPollId(),
                        poll.getQuestion(),
                        poll.getCreatedTime(),
                        poll.isCompleted()
                ))
                .toList();
    }

    @Override
    public void closePoll(UUID pollId) {
        pollRepository.findById(pollId).ifPresent(p -> {
            p.setCompleted(true);
            pollRepository.save(p);
        });
    }

    @Override
    public InsightView getInsights(UUID pollId) {
        //Just a quick implementation based on my dto example, its like an onion with optionCount being the inner layer
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new IllegalArgumentException("Poll not found: " + pollId));

        List<InsightView.OptionCount> voteOptionCount = poll.getVoteOptionList().stream()
                .map(voteOption -> new InsightView.OptionCount(
                        voteOption.getVoteOptionId(),
                        voteOption.getCaption(),
                        (int) voteRepository.countByPollAndChosenOption(poll, voteOption)
                ))
                .toList();

        InsightView.QuestionInsight question = new InsightView.QuestionInsight(
                poll.getPollId(),
                poll.getQuestion(),
                voteOptionCount
        );

        return new InsightView(
                poll.getPollId(),
                poll.getQuestion(),
                List.of(question)
        );
    }
}
