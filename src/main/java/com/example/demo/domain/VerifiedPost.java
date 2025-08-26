package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "verified_posts")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class VerifiedPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(length = 100)
    private String title;

    @Column(length = 200)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private VerifiedPostCategory category;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @Builder.Default
    private List<VerifiedComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @Builder.Default
    private List<VerifiedLike> likes = new ArrayList<>();
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
    /**
     * 게시글 수정
     */
    public void updatePost(String title, String content, VerifiedPostCategory category) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title;
        }
        if (content != null) {
            this.content = content;
        }
        if (category != null) {
            this.category = category;
        }
    }
    
    /**
     * 게시글 삭제 (Soft Delete)
     */
    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
    
    /**
     * 삭제 여부 확인
     */
    public boolean isDeleted() {
        return deletedAt != null;
    }
}