package com.example.demo.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private String content;
    private String author;
}
