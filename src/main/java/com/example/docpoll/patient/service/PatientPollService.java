package com.example.docpoll.patient.service;

import java.util.List;
import java.util.UUID;

import com.example.docpoll.patient.dto.PollPatientView;

public interface PatientPollService {
    PollPatientView castVote(UUID pollId, UUID voteOptionId);
    List<PollPatientView> listPolls();
}
