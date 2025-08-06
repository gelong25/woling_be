package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 일반 게시글 좋아요 엔티티
 */
@Entity
@Table(name = "post_likes", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "user_id"}))
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class PostLike {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    
    /**
     * 좋아요 취소 (Soft Delete)
     * updated_at을 현재 시간으로 설정하여 취소 상태로 표시
     */
    public void cancel() {
        this.updatedAt = LocalDateTime.now();
    }
    
    /**
     * 좋아요 활성 상태 확인
     * updated_at이 null이면 활성 상태
     */
    public boolean isActive() {
        return updatedAt == null;
    }
    
    /**
     * 좋아요 취소 상태 확인
     */
    public boolean isCanceled() {
        return updatedAt != null;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        PostLike postLike = (PostLike) obj;
        return Objects.equals(post, postLike.post) && 
               Objects.equals(user, postLike.user);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(post, user);
    }
}