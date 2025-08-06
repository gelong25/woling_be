package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.domain.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(Long postId, CommentDto dto);
    List<Comment> getCommentsByPost(Long postId);
    Comment updateComment(Long commentId, CommentDto dto);
    void deleteComment(Long commentId);
}
