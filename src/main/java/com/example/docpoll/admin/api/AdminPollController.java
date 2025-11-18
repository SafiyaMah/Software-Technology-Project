package com.example.docpoll.admin.api;

import com.example.docpoll.admin.dto.CreatePollRequest;
import com.example.docpoll.admin.dto.InsightView;
import com.example.docpoll.admin.dto.PollAdminView;
import com.example.docpoll.admin.service.AdminPollService;
import com.example.docpoll.domain.User;

import lombok.RequiredArgsConstructor;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/polls")
@RequiredArgsConstructor
public class AdminPollController {
    private final AdminPollService adminPollService;

    //Put in example for how keycloak is implemented on methods, maybe wait til later in project to touch?

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public PollAdminView createPoll(@RequestBody CreatePollRequest request,  @AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getSubject());
        String username = jwt.getClaim("preferred_username");
        User adminUser = new User(userId, username, "ADMIN");
        return adminPollService.createPoll(request, adminUser);
    }

    @GetMapping("/{pollId}/insights")
    @PreAuthorize("hasRole('ADMIN')")
    public InsightView getInsights(@PathVariable UUID pollId) {
        return adminPollService.getInsights(pollId);
    }

    @PostMapping("/{pollId}/close")
    @PreAuthorize("hasRole('ADMIN')")
    public void closePoll(@PathVariable("pollId") UUID pollId){
        adminPollService.closePoll(pollId);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<PollAdminView> listPolls(){
        return adminPollService.listPolls();
    }

    @GetMapping("/{pollId}")
    @PreAuthorize("hasRole('ADMIN')")
    public PollAdminView getPoll(@PathVariable UUID pollId) {
        return adminPollService.getPoll(pollId);
    }
}
