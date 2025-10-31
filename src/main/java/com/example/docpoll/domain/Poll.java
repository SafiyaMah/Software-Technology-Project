package com.example.docpoll.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "polls")
public class Poll {
    @Id
    @GeneratedValue
    private UUID pollId;

    @Column(nullable = false)
    private String question;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    private List<VoteOption> voteOptionList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "creator")
    private User creator;

    @Column(nullable = false)
    private Instant createdTime = Instant.now();

    @Column(nullable = false)
    private boolean completed = false;

    public Poll(){}
    public Poll(String question, User creator){
        this.question = question;
        this.creator = creator;
    }

    //HELPERS
    //------------------------------------------------//
    public void addVoteOption(VoteOption voteOption){
        voteOption.setPoll(this);
        voteOption.setPresentationOrder(voteOptionList.size());
        this.voteOptionList.add(voteOption);
    }
    public void removeVoteOption(VoteOption voteOption){
        this.voteOptionList.remove(voteOption);
        voteOption.setPoll(null);
    }
    public void pollComplete(){
        this.completed = true;
    }
    //------------------------------------------------//


    //GETTERS/SETTERS
    //----------------------------------------------------------//
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public List<VoteOption> getVoteOptionList() {
        return voteOptionList;
    }
    //Rather try to only use add- and removeVoteOption than this cause reasons
    public void setVoteOptionList(List<VoteOption> voteOptionList) {
        this.voteOptionList = voteOptionList;
    }
    public User getCreator() {
        return creator;
    }
    public void setCreator(User creator) {
        this.creator = creator;
    }
    public UUID getPollId() {
        return pollId;
    }
    public void setPollId(UUID pollId) {
        this.pollId = pollId;
    }
    public Instant getCreatedTime() {
        return createdTime;
    }
    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    //--------------------------------------------------//
}
