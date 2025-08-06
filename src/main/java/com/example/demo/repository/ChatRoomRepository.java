package com.example.demo.repository;

import com.example.demo.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {

    @Query("SELECT DISTINCT cr " +
            "FROM ChatRoom cr " +
            "JOIN cr.participants cp1 " +
            "JOIN cr.participants cp2 " +
            "WHERE cp1.user.userId = :userId " +
            "AND cp2.user.userId =:targetUserId")
    Optional<ChatRoom> findByParticipants(@Param("userId") Long userId,
                                          @Param("targetUserId") Integer targetUserId);

    @Query("SELECT DISTINCT cr " +
            "FROM ChatRoom cr " +
            "JOIN cr.participants cp " +
            "WHERE cp.user.userId = :userId")
    List<ChatRoom> findByUserId(@Param("userId") Integer userId);
}