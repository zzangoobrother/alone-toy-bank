package com.example.bankmember.domain;

public enum Role {
    ROLE_ADMIN("admin"),
    ROLE_MEMBER("member");

    private String role;

    Role(String role) {
        this.role = role;
    }
}
