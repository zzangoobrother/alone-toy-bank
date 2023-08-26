package com.example.bankproduct.domain;

public enum ProductState {
    ACTIVITY("activity"),
    INACTIVITY("inactivity");

    private final String state;

    ProductState(String state) {
        this.state = state;
    }
}
