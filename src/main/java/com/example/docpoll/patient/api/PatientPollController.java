package com.example.docpoll.patient.api;

import com.example.docpoll.patient.dto.PollPatientView;
import com.example.docpoll.patient.dto.VoteRequest;
import com.example.docpoll.patient.service.PatientPollService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patient/polls")
@RequiredArgsConstructor
public class PatientPollController {
    private final PatientPollService patientPollService;

    // GET /api/patient/polls
    // List polls for the patient that admin made
    @GetMapping
    public List<PollPatientView> listPolls(){
        return patientPollService.listPolls();
    }

    // POST /api/patient/polls/{pollId}/vote
    // Body: { "optionId;  }
    // Patient get to votes on one option in the poll
    @PostMapping("/{pollId}/vote")
    // KEYCLOAK @PreAuthorize("hasRole('PATIENT')")
    public PollPatientView castVote(@PathVariable UUID pollId, @RequestBody VoteRequest voteRequest){
        return patientPollService.castVote(pollId, voteRequest.voteOptionId());
    }
}
