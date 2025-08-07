package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomCreateDto {
    @NotNull(message = "대상 사용자 ID는 필수입니다")
    private Long targetUserId;

    // 첫 메시지
    private String initialMessage;

    // targetUserId만 받는 생성자
    public ChatRoomCreateDto(Long targetUserId) {
        this.targetUserId = targetUserId;
    }
}