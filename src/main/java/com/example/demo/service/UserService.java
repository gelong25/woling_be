package com.example.demo.service;



import com.example.demo.dto.UserUpdateRequest;


/**
 * 사용자 서비스 인터페이스
 */
public interface UserService {
    
    /**

     * 회원 탈퇴
     * @param userId 탈퇴할 사용자 ID
     */
    void withdrawUser(String userId);

     * 회원 정보 수정
     * @param userId 사용자 ID
     * @param request 수정할 정보
     */
    void updateUserInfo(String userId, UserUpdateRequest request);

}