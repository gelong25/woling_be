package com.example.demo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private final String code;
    private final String message;
    private final LocalDateTime timestamp;
    private final List<ValidationError> validationErrors;

    private ErrorResponse(String code, String message, List<ValidationError> validationErrors) {
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.validationErrors = validationErrors;
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getCode(), errorCode.getMessage(), null);
    }

    public static ErrorResponse of(ErrorCode errorCode, List<ValidationError> validationErrors) {
        return new ErrorResponse(errorCode.getCode(), errorCode.getMessage(), validationErrors);
    }

    public static ErrorResponse of(ErrorCode errorCode, List<ValidationError> validationErrors, String customMessage) {
        return new ErrorResponse(errorCode.getCode(), customMessage, validationErrors);
    }

    public static ErrorResponse of(String code, String message) {
        return new ErrorResponse(code, message, null);
    }

    public static ErrorResponse of(String code, String message, List<ValidationError> validationErrors) {
        return new ErrorResponse(code, message, validationErrors);
    }

    public String getCode() { return code; }
    public String getMessage() { return message; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public List<ValidationError> getValidationErrors() { return validationErrors; }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ValidationError {
        private final String field;
        private final String message;
        private final Object rejectedValue;

        private ValidationError(String field, String message, Object rejectedValue) {
            this.field = field;
            this.message = message;
            this.rejectedValue = rejectedValue;
        }

        public static ValidationError of(String field, String message, Object rejectedValue) {
            return new ValidationError(field, message, rejectedValue);
        }

        public String getField() { return field; }
        public String getMessage() { return message; }
        public Object getRejectedValue() { return rejectedValue; }
    }
}