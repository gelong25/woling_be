package com.example.demo.service;

import com.example.demo.dto.PostLikeResponse;

/**
 * 게시글 좋아요 서비스 인터페이스
 */
public interface PostLikeService {
    
    /**
     * 게시글 좋아요 토글 (좋아요/좋아요 취소)
     * 
     * @param postId 게시글 ID
     * @param userId 사용자 ID
     * @return 좋아요 상태 및 개수
     */
    PostLikeResponse togglePostLike(Long postId, String userId);
}