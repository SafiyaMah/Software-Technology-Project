package com.example.docpoll.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue
    private UUID voteId;

    @ManyToOne
    @JoinColumn(name = "vote_option_id")
    private VoteOption chosenOption;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    public Vote(){}
    public Vote(VoteOption chosenOption, User user) {
        this.chosenOption = chosenOption;
        this.user = user;
        if(chosenOption != null){
            this.poll = chosenOption.getPoll();
        } else{
            this.poll = null;
        }
    }


    //GETTERS/SETTERS
    //-------------------------------//
    public UUID getVoteId() {
        return voteId;
    }
    public void setVoteId(UUID voteId) {
        this.voteId = voteId;
    }
    public VoteOption getChosenOption() {
        return chosenOption;
    }
    public void setChosenOption(VoteOption chosenOption) {
        this.chosenOption = chosenOption;
        if(chosenOption != null){
            this.poll = chosenOption.getPoll();
        }
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Poll getPoll() {
        return poll;
    }
    public void setPoll(Poll poll) {
        this.poll = poll;
    }
    //------------------------------//
}
