package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "verified_likes")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class VerifiedLike {
    @EmbeddedId
    private VerifiedLikeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId")
    @JoinColumn(name = "post_id")
    private VerifiedPost post;

    @Column(name = "liked_at", nullable = false)
    private LocalDateTime likedAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        this.likedAt = LocalDateTime.now();
    }
    
    /**
     * 좋아요 취소 (Soft Delete)
     */
    public void cancel() {
        this.updatedAt = LocalDateTime.now();
    }
    
    /**
     * 좋아요 활성 상태 확인
     */
    public boolean isActive() {
        return updatedAt == null;
    }
}

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
class VerifiedLikeId implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "post_id")
    private Long postId;
}