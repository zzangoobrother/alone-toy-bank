package com.example.bankapi.global.exception;

import com.example.bankcommon.exception.BusinessException;
import com.example.bankcommon.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<ErrorResponse.FieldError> result = fieldErrors.stream()
                .map(error -> ErrorResponse.FieldError.of(
                        error.getField(),
                        error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                        error.getDefaultMessage()
                )).toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, result));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        return ResponseEntity.status(ErrorCode.METHOD_NOT_ALLOWED.getStatus()).body(ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED));
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        log.error("handleBusinessException", e);
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.status(HttpStatus.valueOf(errorCode.getStatus())).body(ErrorResponse.of(errorCode));
    }

    @ExceptionHandler(InvalidTokenException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidTokenException(InvalidTokenException e) {
        log.error("handleBusinessException", e);
        ApiErrorCode errorCode = e.getApiErrorCode();
        return ResponseEntity.status(HttpStatus.valueOf(errorCode.getStatus())).body(ErrorResponse.of(errorCode.getMessage(), errorCode.getStatus(), errorCode.getCode()));
    }
}
