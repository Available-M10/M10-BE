package com.example.m10.domain.node.domain.repository;

import com.example.m10.domain.node.domain.entity.trigger.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findAllByProjectIdOrderByCreatedAtAsc(Long projectId);
}
