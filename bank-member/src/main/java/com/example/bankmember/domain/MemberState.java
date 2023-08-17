package com.example.bankmember.domain;

public enum MemberState {
    ACTIVITY("activity"),
    INACTIVITY("inactivity");

    private final String state;

    MemberState(String state) {
        this.state = state;
    }
}
