package com.example.docpoll.admin.dto;

import java.util.List;
import java.util.UUID;

//TODO: tuning/can maybe decide to extend for user-specific details/create a dto for it
public record InsightView(
        UUID pollId,
        String question,
        List<QuestionInsight> questions
) {
    public record QuestionInsight(
            UUID questionId,
            String caption,
            List<OptionCount> options
    ){}
    public record OptionCount(
            UUID optionId,
            String label,
            int count
    ){}
}
