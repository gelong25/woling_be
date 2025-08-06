package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 사용자 엔티티
 */
@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class User {
    
    @Id
    @Column(name = "user_id")
    private String userId;
    
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Column(length = 100)
    private String country;
    
    @Column
    private int age;

    @Column(name = "profile_image_url", length = 200)
    private String profileImageUrl;
    
    @Column(name = "gender_verified")
    private Boolean genderVerified;
    
    @Column(length = 100)
    private String language;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
    /**
     * 회원 탈퇴 (Soft Delete)
     */
    public void withdraw() {
        this.deletedAt = LocalDateTime.now();
    }
    
    /**
     * 탈퇴 여부 확인
     */
    public boolean isDeleted() {
        return deletedAt != null;
    }
    
    /**
     * 활성 상태 확인
     */
    public boolean isActive() {
        return deletedAt == null;
    }
}