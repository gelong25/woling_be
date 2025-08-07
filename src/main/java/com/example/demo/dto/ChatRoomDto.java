package com.example.demo.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChatRoomDto {
    private Long roomId;
    private Long userId;                    // 상대방 사용자 ID
    private String name;                    // 상대방 이름
    private String profileImageUrl;         // 상대방 프로필 이미지
    private LastMessageDto lastMessage;
    private LocalDateTime createdAt;
    private Integer unreadCount;            // 읽지 않은 메시지 수

    // 필수 필드 생성자
    public ChatRoomDto(Long roomId, Long userId, String name, LocalDateTime createdAt) {
        this.roomId = roomId;
        this.userId = userId;
        this.name = name;
        this.createdAt = createdAt;
    }

    // 내부 클래스: 마지막 메시지 정보
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class LastMessageDto {
        private String content;
        private LocalDateTime timestamp;
        private Long senderId;  // 마지막 메시지 발신자 ID
        private String senderName;  // 마지막 메시지 발신자 이름

        // 필수 필드 생성자
        public LastMessageDto(String content, LocalDateTime timestamp) {
            this.content = content;
            this.timestamp = timestamp;
        }
    }
}