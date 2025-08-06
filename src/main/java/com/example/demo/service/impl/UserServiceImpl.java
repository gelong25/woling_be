package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 사용자 서비스 구현체
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    @Override
    @Transactional
    public void withdrawUser(String userId) {
        log.info("회원 탈퇴 요청: userId={}", userId);
        
        // 활성 사용자 조회
        User user = userRepository.findActiveByUserId(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        
        // 이미 탈퇴한 사용자 체크 (안전장치)
        if (user.isDeleted()) {
            throw new BusinessException(ErrorCode.USER_ALREADY_DELETED);
        }
        
        // 회원 탈퇴 처리 (Soft Delete)
        user.withdraw();
        
        log.info("회원 탈퇴 완료: userId={}, deletedAt={}", userId, user.getDeletedAt());
    }
}