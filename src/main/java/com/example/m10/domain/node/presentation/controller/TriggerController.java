package com.example.m10.domain.node.presentation.controller;

import com.example.m10.domain.node.presentation.dto.request.ChatRequestDto;
import com.example.m10.domain.node.presentation.dto.response.ChatResponseDto;
import com.example.m10.domain.node.service.kind.trigger.CreateChatMessageTriggerService;
import com.example.m10.domain.node.service.kind.trigger.QueryAllChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trigger")
public class TriggerController {
    private final CreateChatMessageTriggerService chatMessageTriggerService;
    private final QueryAllChatService queryAllChatService;

    @PostMapping("/{projectId}/chat")
    public ChatResponseDto trigger(
            @PathVariable Long projectId,
            @Valid @RequestBody ChatRequestDto dto,
            @RequestParam String startPortId
    ){
        return chatMessageTriggerService.trigger(startPortId, projectId, dto);
    }

    @GetMapping("/{projectId}/chat")
    public List<ChatResponseDto> getAllNode(@PathVariable Long projectId){
        return queryAllChatService.queryAllMessage(projectId);
    }
}
