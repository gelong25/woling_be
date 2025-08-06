package com.example.demo.repository;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 게시글 Repository
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    
    /**
     * 삭제되지 않은 게시글 조회
     */
    @Query("SELECT p FROM Post p WHERE p.postId = :postId AND p.deletedAt IS NULL")
    Optional<Post> findByIdAndNotDeleted(@Param("postId") Long postId);
    
    /**
     * 사용자별 게시글 조회 (삭제되지 않은 것만)
     */
    @Query("SELECT p FROM Post p WHERE p.user = :user AND p.deletedAt IS NULL ORDER BY p.createdAt DESC")
    List<Post> findByUserAndNotDeleted(@Param("user") User user);
    
    /**
     * 모든 게시글 조회 (삭제되지 않은 것만)
     */
    @Query("SELECT p FROM Post p WHERE p.deletedAt IS NULL ORDER BY p.createdAt DESC")
    List<Post> findAllNotDeleted();
}