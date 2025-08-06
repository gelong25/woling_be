package com.example.demo.exception;

import org.springframework.http.HttpStatus;

/**
 * 모든 비즈니스 예외를 여기서 Enum으로 관리
 * 추가하고 싶으면 알아서 추가하면 됨
 */
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("C001", "서버 내부 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_INPUT_VALUE("C002", "잘못된 입력값입니다.", HttpStatus.BAD_REQUEST),
    INVALID_INPUT("C006", "입력한 정보가 유효하지 않습니다.", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST_BODY("C003", "요청 본문이 잘못되었습니다.", HttpStatus.BAD_REQUEST),
    MISSING_REQUIRED_PARAMETER("C004", "필수 파라미터가 누락되었습니다: %s", HttpStatus.BAD_REQUEST),
    INVALID_DATE_FORMAT("C005", "날짜 형식이 잘못되었습니다.", HttpStatus.BAD_REQUEST),
    
    RESOURCE_NOT_FOUND("R001", "요청한 리소스를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("R002", "사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    POST_NOT_FOUND("R003", "해당 게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    
    UNAUTHORIZED("B001", "로그인이 필요합니다.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED_ACCESS("B002", "접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    DUPLICATE_RESOURCE("B003", "이미 존재하는 리소스입니다.", HttpStatus.CONFLICT),
    INVALID_OPERATION("B004", "유효하지 않은 작업입니다.", HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR("B005", "좋아요 처리 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String formatMessage(Object... args) {
        return String.format(message, args);
    }
}