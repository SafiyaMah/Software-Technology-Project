package com.example.docpoll.admin.service;

import com.example.docpoll.admin.dto.InsightView;
import com.example.docpoll.admin.dto.PollAdminView;

import java.util.List;
import java.util.UUID;

//TODO: probably add a few as per necessity
public interface AdminPollService {
    PollAdminView createPoll();//TODO
    PollAdminView getPoll();//TODO
    List<PollAdminView> listPolls();
    void closePoll(UUID pollId);
    InsightView getInsights(UUID pollId);//TODO
}
