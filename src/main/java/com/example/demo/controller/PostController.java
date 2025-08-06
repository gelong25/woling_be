package com.example.demo.controller;

import com.example.demo.domain.Post;
import com.example.demo.dto.PostDto;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public Post createPost(@RequestBody PostDto dto) {
        return postService.createPost(dto);
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostDto dto) {
        return postService.updatePost(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
