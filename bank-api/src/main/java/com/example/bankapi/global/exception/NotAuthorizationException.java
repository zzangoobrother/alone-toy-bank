package com.example.bankapi.global.exception;

import lombok.Getter;

@Getter
public class NotAuthorizationException extends RuntimeException {

    private final ApiErrorCode apiErrorCode;

    public NotAuthorizationException(ApiErrorCode apiErrorCode) {
        this.apiErrorCode = apiErrorCode;
    }
}
