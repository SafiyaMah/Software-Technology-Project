package com.example.docpoll.patient.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record PollPatientView(
        UUID pollId,
        String question,
        Instant createdTime,
        boolean completed,
        List<OptionView> options
) {
        public record OptionView(
                UUID voteOptionId,
                String caption
        ) {}
}
