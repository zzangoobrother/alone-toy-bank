package com.example.bankapi.global.exception;

import lombok.Getter;

@Getter
public enum ApiErrorCode {
    LOGIN_INPUT_VALUE(400, "A001", "로그인 후 이용해주세요."),
    EFFECTIVE_TOKEN_VALUE(400, "A002", "유효한 토큰이 아닙니다."),
    NOT_AUTHORIZATION(401, "A003", "접급 권한이 없습니다.");

    private final int status;
    private final String code;
    private final String message;

    ApiErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
