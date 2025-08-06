package com.example.demo.repository;

import com.example.demo.domain.ChatRoomParticipant;
import com.example.demo.domain.ChatRoomParticipantId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomParticipantRepository extends JpaRepository<ChatRoomParticipant, ChatRoomParticipantId> {

    @Query("SELECT cp FROM ChatRoomParticipant cp " +
            "WHERE cp.chatRoom.chatRoomId = :chatRoomId")
    List<ChatRoomParticipant> findByChatRoomId(@Param("chatRoomId") Long chatRoomId);

    @Query("SELECT cp FROM ChatRoomParticipant cp " +
            "WHERE cp.user.userId = :userId")
    List<ChatRoomParticipant> findByUserId(@Param("userId") Long userId);

    @Query("SELECT cp FROM ChatRoomParticipant cp " +
            "WHERE cp.chatRoom.chatRoomId = :chatRoomId " +
            "AND cp.user.userId = :userId")
    Optional<ChatRoomParticipant> findByChatRoomIdAndUserId(@Param("chatRoomId") Long chatRoomId,
                                                            @Param("userId") Long userId);

    // 특정 채팅방에 특정 사용자가 참여하고 있는지 확인
    @Query("SELECT CASE WHEN COUNT(cp) > 0 THEN true ELSE false END " +
            "FROM ChatRoomParticipant cp " +
            "WHERE cp.chatRoom.chatRoomId = :chatRoomId " +
            "AND cp.user.userId = :userId")
    boolean existsByChatRoomIdAndUserId(@Param("chatRoomId") Long chatRoomId,
                                        @Param("userId") Long userId);
}