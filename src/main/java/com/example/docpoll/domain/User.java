package com.example.docpoll.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(length = 32)
    private String username;

    //Most user related stuff handled in keycloak so i keep lean, can still add a display name etc.

    public User(){}
    public User(String username){
        this.username = username;
    }

    //GETTERS/SETTERS
    //--------------------------------------------//
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    //----------------------------------------------//
}
