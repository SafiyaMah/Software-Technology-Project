package com.example.docpoll.admin.dto;

import java.time.Instant;
import java.util.UUID;

public record PollAdminView(
        UUID pollId,
        String question,
        Instant createdTime,
        boolean completed
        //TODO: add/change parameters
) {
}
