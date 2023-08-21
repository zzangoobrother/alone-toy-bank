package com.example.bankapi.global.exception;

import lombok.Getter;

@Getter
public class InvalidTokenException extends RuntimeException {

    private final ApiErrorCode apiErrorCode;

    public InvalidTokenException(ApiErrorCode apiErrorCode) {
        this.apiErrorCode = apiErrorCode;
    }
}
