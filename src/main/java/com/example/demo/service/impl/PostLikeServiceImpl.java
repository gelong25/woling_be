package com.example.demo.service.impl;

import com.example.demo.domain.Post;
import com.example.demo.domain.PostLike;
import com.example.demo.domain.User;
import com.example.demo.dto.PostLikeResponse;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.PostLikeRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PostLikeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 게시글 좋아요 서비스 구현체
 */
@Service
@Transactional
public class PostLikeServiceImpl implements PostLikeService {
    
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostLikeRepository postLikeRepository;
    
    public PostLikeServiceImpl(PostRepository postRepository, 
                              UserRepository userRepository,
                              PostLikeRepository postLikeRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postLikeRepository = postLikeRepository;
    }
    
    @Override
    public PostLikeResponse togglePostLike(Long postId, String userId) {
        try {
            // 사용자 조회
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new BusinessException(ErrorCode.UNAUTHORIZED));
            
            // 게시글 조회 (삭제되지 않은 것만)
            Post post = postRepository.findByIdAndNotDeleted(postId)
                    .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));
            
            // 기존 좋아요 여부 확인 (모든 상태)
            Optional<PostLike> existingLike = postLikeRepository.findByPostAndUser(post, user);
            
            boolean liked;
            if (existingLike.isPresent()) {
                PostLike postLike = existingLike.get();
                if (postLike.isActive()) {
                    // 활성 좋아요인 경우 → 좋아요 취소 (soft delete)
                    postLike.cancel();
                    postLikeRepository.save(postLike);
                    liked = false;
                } else {
                    // 취소된 좋아요인 경우 → 다시 활성화 (updated_at을 null로)
                    // 새로운 좋아요 레코드 생성
                    PostLike newLike = PostLike.builder()
                            .post(post)
                            .user(user)
                            .build();
                    postLikeRepository.save(newLike);
                    liked = true;
                }
            } else {
                // 좋아요 기록이 없는 경우 → 새 좋아요 생성
                PostLike newLike = PostLike.builder()
                        .post(post)
                        .user(user)
                        .build();
                postLikeRepository.save(newLike);
                liked = true;
            }
            
            // 현재 활성 좋아요 개수 조회
            long likeCount = postLikeRepository.countActiveByPost(post);
            
            return new PostLikeResponse(liked, likeCount);
            
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INTERNAL_ERROR);
        }
    }
}