package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ChatRoomParticipantId implements Serializable {
    @Column(name = "chat_room_id")
    private Long chatRoomId;

    @Column(name = "user_id")
    private Long userId;

}