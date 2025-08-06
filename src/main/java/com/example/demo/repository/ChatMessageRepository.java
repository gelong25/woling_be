package com.example.demo.repository;

import com.example.demo.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByChatRoom_ChatRoomIdOrderBySentAtAsc(Long chatRoomId);

    @Query("SELECT cm FROM ChatMessage cm " +
            "WHERE cm.chatRoom.chatRoomId = :chatRoomId " +
            "ORDER BY cm.sentAt DESC")
    List<ChatMessage> findLastMessageByChatRoomId(@Param("chatRoomId") Long chatRoomId);

    // 가장 최근 메시지 1개만 가져오기
    @Query("SELECT cm FROM ChatMessage cm " +
            "WHERE cm.chatRoom.chatRoomId = :chatRoomId " +
            "ORDER BY cm.sentAt DESC " +
            "LIMIT 1")
    Optional<ChatMessage> findTopByChatRoomIdOrderBySentAtDesc(@Param("chatRoomId") Long chatRoomId);
}