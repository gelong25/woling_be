package com.example.demo.service;

import com.example.demo.dto.PostDto;
import com.example.demo.domain.Post;

import java.util.List;

public interface PostService {
    Post createPost(PostDto dto);
    Post getPost(Long id);
    List<Post> getAllPosts();
    Post updatePost(Long id, PostDto dto);
    void deletePost(Long id);
}
