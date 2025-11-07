package com.example.docpoll.admin.dto;

import java.util.List;

public record CreatePollRequest(
        String question,
        List<String> options
) {}