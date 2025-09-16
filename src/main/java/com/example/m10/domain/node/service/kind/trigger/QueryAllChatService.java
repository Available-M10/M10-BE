package com.example.m10.domain.node.service.kind.trigger;

import com.example.m10.domain.node.domain.repository.ChatMessageRepository;
import com.example.m10.domain.node.presentation.dto.response.ChatResponseDto;
import com.example.m10.domain.project.facade.ProjectFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryAllChatService {
    private final ChatMessageRepository chatMessageRepository;
    private final ProjectFacade projectFacade;

    @Transactional(readOnly = true)
    public List<ChatResponseDto> queryAllMessage(Long projectId){
        projectFacade.findByProject(projectId);
        return chatMessageRepository.findAllByProjectIdOrderByCreatedAtAsc(projectId).stream()
                .map(n -> ChatResponseDto.from(n.getRole().name(), n.getContent()))
                .toList();
    }
}
