package com.example.demo.service;

/**
 * 사용자 서비스 인터페이스
 */
public interface UserService {
    
    /**
     * 회원 탈퇴
     * @param userId 탈퇴할 사용자 ID
     */
    void withdrawUser(String userId);
}