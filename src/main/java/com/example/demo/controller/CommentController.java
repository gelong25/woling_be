package com.example.demo.controller;

import com.example.demo.domain.Comment;
import com.example.demo.dto.CommentDto;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Comment addComment(@PathVariable Long postId, @RequestBody CommentDto dto) {
        return commentService.addComment(postId, dto);
    }

    @GetMapping
    public List<Comment> getComments(@PathVariable Long postId) {
        return commentService.getCommentsByPost(postId);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentDto dto) {
        return commentService.updateComment(commentId, dto);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
