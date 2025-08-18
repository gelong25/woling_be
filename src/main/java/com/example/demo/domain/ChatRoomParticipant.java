package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_room_participant",
        indexes = {@Index(name = "idx_participant_room_user", columnList = "chat_room_id, user_id")})
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomParticipant {
    @EmbeddedId
    private ChatRoomParticipantId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("chatRoomId")
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreatedDate
    @Column(name = "joined_at", nullable = false, updatable = false)
    private LocalDateTime joinedAt;

    // 편의 생성자
    // ChatRoom과 User로 ChatRoomParticipant 생성
    public static ChatRoomParticipant of(ChatRoom room, User user) {
        ChatRoomParticipant p = new ChatRoomParticipant();
        p.setId(new ChatRoomParticipantId(room.getChatRoomId(), user.getUserId()));
        p.setChatRoom(room);
        p.setUser(user);
        return p;
    }

}