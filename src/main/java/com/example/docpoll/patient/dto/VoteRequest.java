package com.example.docpoll.patient.dto;

import java.util.UUID;

public record VoteRequest(
        UUID voteOptionId
) {}
