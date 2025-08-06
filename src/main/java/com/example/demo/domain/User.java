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

     * 회원 정보 업데이트
     */
    public void updateUserInfo(String name, String country, String language) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
        if (country != null) {
            this.country = country.trim().isEmpty() ? null : country.trim();
        }
        if (language != null) {
            this.language = language.trim().isEmpty() ? null : language.trim();
        }

    }
}