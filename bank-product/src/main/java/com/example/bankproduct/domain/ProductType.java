package com.example.bankproduct.domain;

public enum ProductType {
    LOAN("loan"),
    DEPOSIT("deposit");

    private String type;

    ProductType(String type) {
        this.type = type;
    }
}
