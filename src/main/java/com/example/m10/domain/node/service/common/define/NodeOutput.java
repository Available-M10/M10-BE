package com.example.m10.domain.node.service.common.define;

import com.example.m10.domain.node.presentation.dto.response.ChatResponseDto;


public interface NodeOutput {
    record None() implements NodeOutput {}
    record Chat(ChatResponseDto response) implements NodeOutput {
        public ChatResponseDto response() { return response; }
    }

    static NodeOutput none() {
        return new None();
    }
    static NodeOutput chat(ChatResponseDto response) {
        return new Chat(response);
    }

}
