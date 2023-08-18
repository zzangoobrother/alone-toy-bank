package com.example.bankcommon.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "C001", "필수 입력값 입니다."),
    METHOD_NOT_ALLOWED(405, "C002", "해당 경로를 찾을 수 없습니다."),
    ENTITY_NOT_FOUND(400, "C003", "해당 정보를 찾을 수 없습니다.");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
