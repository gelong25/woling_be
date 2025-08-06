package com.example.demo.service.impl;

import com.example.demo.domain.Post;
import com.example.demo.dto.PostDto;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Post createPost(PostDto dto) {
        Post post = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(dto.getAuthor())
                .build();
        return postRepository.save(post);
    }

    @Override
    public Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post updatePost(Long id, PostDto dto) {
        Post post = getPost(id);
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setAuthor(dto.getAuthor());
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
