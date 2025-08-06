package com.example.demo.controller;
import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*** 사용자 관련 API 컨트롤러
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    
    private final UserService userService;
    
    /** 회원 탈퇴 (Soft Delete) */
    @DeleteMapping("/me")
    public ResponseEntity<ApiResponse<Void>> withdrawUser(
            @RequestHeader("X-User-Id") Long userId) {
        
        log.info("회원 탈퇴 API 호출: userId={}", userId);
        
        // 헤더 검증
        if (userId == null) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }
        
        // 회원 탈퇴 처리
        userService.withdrawUser(userId);
        
        return ResponseEntity.ok(ApiResponse.success("회원 탈퇴가 완료되었습니다.", null));
    }
    
    /** 회원 정보 수정 */
    @PutMapping("/me")
    public ResponseEntity<ApiResponse<Void>> updateUserInfo(
            @RequestHeader("X-User-Id") Long userId,
            @RequestBody UserUpdateRequest request) {
        
        // 헤더 검증
        if (userId == null) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }
        
        // 서비스 호출
        userService.updateUserInfo(userId, request);
        
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}