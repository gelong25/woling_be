package com.example.demo.domain;

import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCode;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    
    @Column(name = "user_name", nullable = false, length = 20)
    private String userName;
    
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    @Setter
    @Column(name = "bank_name", length = 10)
    private String bankName;
    
    @Setter
    @Column(name = "account_number", length = 30)
    private String accountNumber;
    
    @Column(name = "profile_image_url", length = 255)
    private String profileImageUrl;
    
    @Column(name = "gender_verified")
    private Boolean genderVerified;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Post> posts = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PostLike> postLikes = new ArrayList<>();
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
    /**
     * 계좌 정보 업데이트
     */
    public void updateAccountInfo(String bankName, String accountNumber) {
        if (accountNumber != null && !accountNumber.matches("^[0-9]*$")) {
            throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
        }
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }
    
    /**
     * 프로필 이미지 업데이트
     */
    public void updateProfileImage(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}