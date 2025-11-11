package com.example.docpoll.admin.service;

import com.example.docpoll.admin.dto.CreatePollRequest;
import com.example.docpoll.admin.dto.InsightView;
import com.example.docpoll.admin.dto.PollAdminView;
import com.example.docpoll.domain.Poll;
import com.example.docpoll.domain.VoteOption;
import com.example.docpoll.repository.PollRepository;
import com.example.docpoll.repository.VoteReporistory;
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
    public PollAdminView createPoll(CreatePollRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request is required");
        }
        if (request.question() == null || request.question().isBlank()) {
            throw new IllegalArgumentException("Question is required");
        }
        if (request.options() == null || request.options().size() < 2) {
            throw new IllegalArgumentException("At least two options are required");
        }

        Poll poll = new Poll();
        poll.setQuestion(request.question().trim());

        request.options().forEach(opt -> {
            if (opt != null && !opt.isBlank()) {
                VoteOption option = new VoteOption();
                option.setCaption(opt.trim());
                poll.addVoteOption(option);
            }
        });

        if (poll.getVoteOptionList().size() < 2) {
            throw new IllegalArgumentException("At least two non-empty options are required");
        }

        Poll saved = pollRepository.save(poll);

        return toAdminView(saved);
    }

    @Override
    public PollAdminView getPoll(UUID pollId) {
        Poll poll =  pollRepository.findById(pollId)
        .orElseThrow(() -> new IllegalArgumentException("Poll not found; " + pollId));

        return toAdminView(poll);
    }

    @Override
    public List<PollAdminView> listPolls() {
        return pollRepository.findAll(Sort.by(Sort.Direction.DESC, "createdTime"))
                .stream()
                .map(this::toAdminView)
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

    // Helper to convert entity to DTO
    private PollAdminView toAdminView(Poll poll) {
        List<PollAdminView.OptionView> optionViews = poll.getVoteOptionList().stream()
                .map(o -> new PollAdminView.OptionView(
                        o.getVoteOptionId(),
                        o.getCaption()
                ))
                .toList();

        return new PollAdminView(
                poll.getPollId(),
                poll.getQuestion(),
                poll.getCreatedTime(),
                poll.isCompleted(),
                optionViews
        );
    }
}
