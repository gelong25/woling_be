package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 표준 API 응답 형식
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    
    private final int status;
    private final String message;
    private final T data;
    private final String errorCode;
    
    private ApiResponse(int status, String message, T data, String errorCode) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.errorCode = errorCode;
    }
    
    /**
     * 성공 응답 생성
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "요청에 성공했습니다.", data, null);
    }
    
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(200, message, data, null);
    }
    
    /**
     * 에러 응답 생성
     */
    public static <T> ApiResponse<T> error(int status, String message, String errorCode) {
        return new ApiResponse<>(status, message, null, errorCode);
    }
    
    // Getters
    public int getStatus() { return status; }
    public String getMessage() { return message; }
    public T getData() { return data; }
    public String getErrorCode() { return errorCode; }
}