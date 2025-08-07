package com.example.demo.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChatMessageDto {
    private Long messageId;
    private String content;
    private LocalDateTime sentAt;
    private Long chatRoomId;
    private Long userId;
    private String senderName;
    private String profileImageUrl;

    // 메시지 생성용 생성자
    public ChatMessageDto(String content, Long chatRoomId, Long userId) {
        this.content = content;
        this.chatRoomId = chatRoomId;
        this.userId = userId;
        this.sentAt = LocalDateTime.now();
    }
}