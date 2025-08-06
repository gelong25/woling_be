package com.example.demo.service;

import com.example.demo.domain.ChatMessage;
import com.example.demo.repository.ChatMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class MessageBufferService {

    private static final Logger logger = LoggerFactory.getLogger(MessageBufferService.class);
    private final ConcurrentLinkedQueue<ChatMessage> messageBuffer = new ConcurrentLinkedQueue<>();

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    // 메시지를 메모리 버퍼에 추가
    public void addMessage(ChatMessage message) {
        messageBuffer.offer(message);
        logger.debug("메시지가 버퍼에 추가됨. 버퍼 크기: {}", messageBuffer.size());
    }

    // 30초마다 버퍼에 있는 메시지들을 DB에 bulk insert
    @Scheduled(fixedRate = 30000) // 30초
    public void flushMessagesToDB() {
        if (messageBuffer.isEmpty()) {
            return;
        }

        List<ChatMessage> messagesToSave = new ArrayList<>();
        ChatMessage message;

        // 버퍼에서 모든 메시지를 추출
        while ((message = messageBuffer.poll()) != null) {
            messagesToSave.add(message);
        }

        if (!messagesToSave.isEmpty()) {
            try {
                chatMessageRepository.saveAll(messagesToSave);
                logger.info("{}개의 메시지가 DB에 저장됨", messagesToSave.size());
            } catch (Exception e) {
                logger.error("메시지 DB 저장 중 오류 발생", e);
                // 실패한 메시지들을 다시 버퍼에 추가
                messagesToSave.forEach(messageBuffer::offer);
            }
        }
    }

    // 서버 종료 시 남은 메시지들을 DB에 저장
    public void flushRemainingMessages() {
        flushMessagesToDB();
    }
}