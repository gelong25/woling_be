package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;


import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 사용자 Repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
    /**
     * 이메일로 사용자 조회
     */
    Optional<User> findByEmail(String email);
    
    /**
     * 이메일 중복 확인
     */
    boolean existsByEmail(String email);

    
    /**
     * 활성 사용자 조회 (탈퇴하지 않은 사용자)
     */
    @Query("SELECT u FROM User u WHERE u.userId = :userId AND u.deletedAt IS NULL")
    Optional<User> findActiveByUserId(@Param("userId") String userId);
    
    /**
     * 활성 사용자 존재 여부 확인
     */
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.userId = :userId AND u.deletedAt IS NULL")
    boolean existsActiveByUserId(@Param("userId") String userId);


}