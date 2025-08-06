package com.example.demo.service.impl;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import com.example.demo.dto.CommentDto;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public Comment addComment(Long postId, CommentDto dto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = Comment.builder()
                .content(dto.getContent())
                .author(dto.getAuthor())
                .post(post)
                .build();

        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return post.getComments();
    }

    @Override
    public Comment updateComment(Long commentId, CommentDto dto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        comment.setContent(dto.getContent());
        comment.setAuthor(dto.getAuthor());

        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
