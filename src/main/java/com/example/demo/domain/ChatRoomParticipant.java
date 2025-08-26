package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_room_participants")
@EntityListeners(AuditingEntityListener.class)
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ChatRoomParticipant {
    @EmbeddedId
    private ChatRoomParticipantId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("chatRoomId")
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    @Column(name = "joined_at")
    private LocalDateTime joinedAt;
}

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
class ChatRoomParticipantId implements Serializable {
    @Column(name = "chat_room_id")
    private Long chatRoomId;

    @Column(name = "user_id")
    private Long userId;
}