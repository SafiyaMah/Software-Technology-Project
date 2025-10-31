package com.example.docpoll.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "vote_options")
public class VoteOption {
    @Id
    @GeneratedValue
    private UUID voteOptionId;

    @Column(nullable = false)
    private String caption;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @Column(nullable = false)
    private int presentationOrder;
    public VoteOption() {}

    //Poll adn presentationOrder gets assigned when addPollOption is called
    public VoteOption(String caption) {
        this.caption = caption;
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
