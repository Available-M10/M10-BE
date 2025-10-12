package com.example.m10.domain.node.mapper;

import com.example.m10.domain.node.exception.DtoConversionException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonMapper {
    private final ObjectMapper objectMapper;

    public String toJson(Object dto){
        try{
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T fromJson(String json, Class<T> type){
        try {
            return objectMapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new DtoConversionException();
        }
    }
}
