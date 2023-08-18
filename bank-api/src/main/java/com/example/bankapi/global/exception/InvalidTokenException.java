package com.example.bankapi.global.exception;

import com.example.bankcommon.exception.BusinessException;
import com.example.bankcommon.exception.ErrorCode;

public class InvalidTokenException extends BusinessException {

    public InvalidTokenException(String message) {
        super(ErrorCode.INVALID_INPUT_VALUE, message);
    }
}
