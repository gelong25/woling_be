package com.example.demo.dto;

/**
 * 좋아요 API 응답 DTO
 */
public class PostLikeResponse {
    
    private final boolean liked;
    private final long likeCount;
    
    public PostLikeResponse(boolean liked, long likeCount) {
        this.liked = liked;
        this.likeCount = likeCount;
    }
    
    // Getters
    public boolean isLiked() { return liked; }
    public long getLikeCount() { return likeCount; }
}