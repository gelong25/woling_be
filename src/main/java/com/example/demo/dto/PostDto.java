package com.example.demo.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private String title;
    private String content;
    private String author;
}
