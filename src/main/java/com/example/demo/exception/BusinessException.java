package com.example.demo.exception;

/**
 * 모든 비즈니스 예외를 처리하는 통합 예외 클래스입니다잇~
 * 아래에서 각자 return 하고 싶은 예외 형태 고른 다음,
 * ErrorCode.java에 만들어놓은 Enum으로 예외 유형 구분하세요~
 */
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
    private final Object[] args;

    /**
     * 기본 ErrorCode로 예외 생성
     */
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.args = null;
    }

    /**
     * ErrorCode + 커스텀 메시지로 예외 생성
     */
    public BusinessException(ErrorCode errorCode, String customMessage) {
        super(customMessage);
        this.errorCode = errorCode;
        this.args = null;
    }

    /**
     * ErrorCode + 파라미터로 메시지 포맷팅하여 예외 생성
     */
    public BusinessException(ErrorCode errorCode, Object... args) {
        super(errorCode.formatMessage(args));
        this.errorCode = errorCode;
        this.args = args;
    }

    /**
     * ErrorCode + 원인 예외로 예외 생성
     */
    public BusinessException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
        this.args = null;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Object[] getArgs() {
        return args;
    }
}