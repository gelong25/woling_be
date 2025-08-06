package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 사용자 서비스 구현체
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    @Override
    @Transactional
    public void updateUserInfo(String userId, UserUpdateRequest request) {
        // 입력 검증
        validateUpdateRequest(request);
        
        // 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        
        // 정보 업데이트
        user.updateUserInfo(request.getName(), request.getCountry(), request.getLanguage());
    }
    
    private void validateUpdateRequest(UserUpdateRequest request) {
        if (request.getName() != null && request.getName().trim().isEmpty()) {
            throw new BusinessException(ErrorCode.INVALID_INPUT);
        }
        
        if (request.getName() != null && request.getName().length() > 100) {
            throw new BusinessException(ErrorCode.INVALID_INPUT);
        }
        
        if (request.getCountry() != null && request.getCountry().length() > 100) {
            throw new BusinessException(ErrorCode.INVALID_INPUT);
        }
        
        if (request.getLanguage() != null && request.getLanguage().length() > 100) {
            throw new BusinessException(ErrorCode.INVALID_INPUT);
        }
    }
}