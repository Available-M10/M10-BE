package com.example.m10.domain.node.exception;

import com.example.m10.global.exception.model.CustomException;
import com.example.m10.global.exception.model.ErrorCode;

public class ChatNodeNotFoundException extends CustomException {
    public ChatNodeNotFoundException(){
        super(ErrorCode.CHAT_NODE_NOT_FOUND);
    }
}
