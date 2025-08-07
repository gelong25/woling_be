package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageCreateDto {
    @NotNull(message = "채팅방 ID는 필수입니다")
    private Long chatRoomId;

    @NotBlank(message = "메시지 내용은 필수입니다")
    @Size(max = 500, message = "메시지는 500자를 초과할 수 없습니다")
    private String content;

    private Long userId;

    public ChatMessageCreateDto(Long chatRoomId, String content) {
        this.chatRoomId = chatRoomId;
        this.content = content;
    }

}