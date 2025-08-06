package com.example.demo.exception;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String LOG_FORMAT = "[{}] {}";

    /**
     * 비즈니스 예외 처리
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        log.error(LOG_FORMAT, "BusinessException[{}]", errorCode.getCode(), ex.getMessage(), ex);
        
        return ResponseEntity.status(errorCode.getStatus())
            .body(ErrorResponse.of(errorCode.getCode(), ex.getMessage()));
    }

    /**
     * 유효성 검증 예외 처리
     */
    @ExceptionHandler({
        DateTimeParseException.class,
        MethodArgumentTypeMismatchException.class
    })
    protected ResponseEntity<ErrorResponse> handleValidationExceptions(Exception ex) {
        log.error(LOG_FORMAT, "ValidationException", ex.getMessage(), ex);
        List<ErrorResponse.ValidationError> validationErrors = createValidationErrors(ex);

        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        return ResponseEntity.status(errorCode.getStatus())
            .body(ErrorResponse.of(errorCode.getCode(), errorCode.getMessage(), validationErrors));
    }

    /**
     * 필수값 누락 예외 처리
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
        MissingServletRequestParameterException ex,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request) {

        log.error(LOG_FORMAT, "MissingParameterException", ex.getMessage(), ex);

        List<ErrorResponse.ValidationError> validationErrors = List.of(
            ErrorResponse.ValidationError.of(
                ex.getParameterName(),
                ErrorCode.MISSING_REQUIRED_PARAMETER.formatMessage(ex.getParameterName()),
                null
            )
        );

        ErrorCode errorCode = ErrorCode.MISSING_REQUIRED_PARAMETER;
        return ResponseEntity.status(errorCode.getStatus())
            .body(ErrorResponse.of(errorCode.getCode(), errorCode.getMessage(), validationErrors));
    }

    /**
     * @Valid 검증 예외 처리
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request) {

        log.error(LOG_FORMAT, "MethodArgumentNotValidException", ex.getMessage(), ex);

        List<ErrorResponse.ValidationError> validationErrors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> ErrorResponse.ValidationError.of(
                error.getField(),
                error.getDefaultMessage(),
                error.getRejectedValue()
            ))
            .collect(Collectors.toList());

        ErrorCode errorCode = ErrorCode.INVALID_REQUEST_BODY;
        return ResponseEntity.status(errorCode.getStatus())
            .body(ErrorResponse.of(errorCode.getCode(), errorCode.getMessage(), validationErrors));
    }

    /**
     * HTTP 메시지 읽기 실패 예외 처리
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException ex,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request) {

        log.error(LOG_FORMAT, "HttpMessageNotReadableException", ex.getMessage(), ex);

        List<ErrorResponse.ValidationError> validationErrors = List.of(
            ErrorResponse.ValidationError.of(
                "request body",
                "요청 본문 형식이 잘못되었습니다.",
                null
            )
        );

        ErrorCode errorCode = ErrorCode.INVALID_DATE_FORMAT;
        return ResponseEntity.status(errorCode.getStatus())
            .body(ErrorResponse.of(errorCode.getCode(), errorCode.getMessage(), validationErrors));
    }

    /**
     * 예상치 못한 예외 처리
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllException(Exception ex) {
        log.error(LOG_FORMAT, "UnexpectedException", ex.getMessage(), ex);
        
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(errorCode.getStatus())
            .body(ErrorResponse.of(errorCode.getCode(), errorCode.getMessage()));
    }

    /**
     * 유효성 검증 에러 생성 헬퍼 메서드
     */
    private List<ErrorResponse.ValidationError> createValidationErrors(Exception ex) {
        if (ex instanceof MethodArgumentTypeMismatchException typeMismatchEx) {
            return List.of(ErrorResponse.ValidationError.of(
                typeMismatchEx.getName(),
                "타입이 올바르지 않습니다.",
                typeMismatchEx.getValue()
            ));
        } else if (ex instanceof DateTimeParseException) {
            return List.of(ErrorResponse.ValidationError.of(
                "dateTime",
                "날짜 형식이 올바르지 않습니다.",
                null
            ));
        }
        
        return List.of(ErrorResponse.ValidationError.of(
            "unknown",
            ex.getMessage(),
            null
        ));
    }
}