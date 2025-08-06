package com.example.demo.repository;

import com.example.demo.domain.Post;
import com.example.demo.domain.PostLike;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 게시글 좋아요 Repository
 */
@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    
    /**
     * 특정 게시글과 사용자의 좋아요 조회 (활성 상태만)
     */
    @Query("SELECT pl FROM PostLike pl WHERE pl.post = :post AND pl.user = :user AND pl.updatedAt IS NULL")
    Optional<PostLike> findActiveByPostAndUser(@Param("post") Post post, @Param("user") User user);
    
    /**
     * 특정 게시글과 사용자의 좋아요 조회 (모든 상태)
     */
    Optional<PostLike> findByPostAndUser(Post post, User user);
    
    /**
     * 특정 게시글과 사용자의 활성 좋아요 존재 여부 확인
     */
    @Query("SELECT COUNT(pl) > 0 FROM PostLike pl WHERE pl.post = :post AND pl.user = :user AND pl.updatedAt IS NULL")
    boolean existsActiveByPostAndUser(@Param("post") Post post, @Param("user") User user);
    
    /**
     * 특정 게시글의 활성 좋아요 개수 조회
     */
    @Query("SELECT COUNT(pl) FROM PostLike pl WHERE pl.post = :post AND pl.updatedAt IS NULL")
    long countActiveByPost(@Param("post") Post post);
}