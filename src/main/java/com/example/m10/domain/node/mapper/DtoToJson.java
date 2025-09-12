package com.example.m10.domain.node.mapper;

import com.example.m10.domain.node.presentation.dto.request.DocumentNodeRequestDto;
import com.example.m10.domain.node.presentation.dto.request.LLMNodeRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DtoToJson {
    private final ObjectMapper objectMapper;

    public String mapper(DocumentNodeRequestDto dto){
        try{
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String mapper(LLMNodeRequestDto dto){
        try{
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
