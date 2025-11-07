package com.example.docpoll.domain;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private UUID userId;

    @Column(length = 32, nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String role; // "PATIENT" or "ADMIN"

    //Most user related stuff handled in keycloak so i keep lean, can still add a display name etc.

    public User(){}
    public User(String username, String role){
        this.username = username;
        this.role = role;
    }

    //GETTERS/SETTERS
    //--------------------------------------------//
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    //----------------------------------------------//
}
