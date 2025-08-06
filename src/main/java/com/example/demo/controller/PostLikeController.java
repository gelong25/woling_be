package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.PostLikeResponse;
import com.example.demo.service.PostLikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 게시글 좋아요 API 컨트롤러
 */
@RestController
@RequestMapping("/api/posts")
public class PostLikeController {
    
    private final PostLikeService postLikeService;
    
    public PostLikeController(PostLikeService postLikeService) {
        this.postLikeService = postLikeService;
    }
    
    /**
     * 게시글 좋아요 토글
     * POST /api/posts/{postId}/like
     * 
     * @param postId 게시글 ID
     * @param userId 현재 로그인한 사용자 ID (임시로 헤더에서 받음)
     * @return 좋아요 상태 및 개수
     */
    @PostMapping("/{postId}/like")
    public ResponseEntity<ApiResponse<PostLikeResponse>> togglePostLike(
            @PathVariable Long postId,
            @RequestHeader(value = "X-User-Id", required = false) String userId) {
        
        // TODO: 실제 구현에서는 JWT 토큰에서 사용자 ID를 추출해야 함
        if (userId == null) {
            return ResponseEntity.status(401)
                    .body(ApiResponse.error(401, "로그인이 필요합니다.", "UNAUTHORIZED"));
        }
        
        PostLikeResponse response = postLikeService.togglePostLike(postId, userId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}