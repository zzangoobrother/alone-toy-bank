package com.example.bankapi.global.exception;

import com.example.bankcommon.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private String message;
    private int status;
    private List<FieldError> errors;
    private String code;

    public ErrorResponse(String message, int status, List<FieldError> errors, String code) {
        this.message = message;
        this.status = status;
        this.errors = errors;
        this.code = code;
    }

    public static ErrorResponse of(ErrorCode error) {
        return new ErrorResponse(error.getMessage(), error.getStatus(), new ArrayList<>(), error.getCode());
    }

    public static ErrorResponse of(ErrorCode error, List<FieldError> fieldErrors) {
        return new ErrorResponse(error.getMessage(), error.getStatus(), fieldErrors, error.getCode());
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        private FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static FieldError of(final String field, final String value, final String reason) {
            return new FieldError(field, value, reason);
        }
    }
}
