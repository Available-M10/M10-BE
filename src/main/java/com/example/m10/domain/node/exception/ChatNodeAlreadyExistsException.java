package com.example.m10.domain.node.exception;

import com.example.m10.global.exception.model.CustomException;
import com.example.m10.global.exception.model.ErrorCode;

public class ChatNodeAlreadyExistsException extends CustomException {
    public ChatNodeAlreadyExistsException(){
        super(ErrorCode.CHAT_NODE_ALREADY_EXISTS);
    }
}
