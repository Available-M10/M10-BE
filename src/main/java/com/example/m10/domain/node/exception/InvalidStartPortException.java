package com.example.m10.domain.node.exception;

import com.example.m10.global.exception.model.CustomException;
import com.example.m10.global.exception.model.ErrorCode;

public class InvalidStartPortException extends CustomException {
    public InvalidStartPortException(){
        super(ErrorCode.INVALID_CHAT_NODE_PORT);
    }
}
