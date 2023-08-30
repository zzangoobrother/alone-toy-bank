package com.example.bankcommon.exception;

public class NotUpdateEntityInactivityException extends BusinessException {

    public NotUpdateEntityInactivityException(String message) {
        super(ErrorCode.ENTITY_NOT_FOUND, message);
    }

    public NotUpdateEntityInactivityException(ErrorCode errorCode) {
        super(errorCode);
    }
}
