package com.example.docpoll.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "vote_options")
public class VoteOption {
    @Id
    @GeneratedValue
    private UUID voteOptionId;
    private String caption;
    @ManyToOne
    private Poll poll;
    private int presentationOrder;
    public VoteOption() {}

    public VoteOption(String caption, Poll poll,  int presentationOrder) {
        this.caption = caption;
        this.poll = poll;
        this.presentationOrder = presentationOrder;
    }


    //GETTERS/SETTERS
    //-----------------------------------------------//
    public UUID getVoteOptionId() {
        return voteOptionId;
    }
    public void setVoteOptionId(UUID voteOptionId) {
        this.voteOptionId = voteOptionId;
    }
    public String getCaption() {
        return caption;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }
    public Poll getPoll() {
        return poll;
    }
    public void setPoll(Poll poll) {
        this.poll = poll;
    }
    public int getPresentationOrder() {
        return presentationOrder;
    }
    public void setPresentationOrder(int presentationOrder) {
        this.presentationOrder = presentationOrder;
    }
    //-------------------------------------------------//
}
