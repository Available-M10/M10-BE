package com.example.m10.domain.node.exception;

import com.example.m10.global.exception.model.CustomException;
import com.example.m10.global.exception.model.ErrorCode;

public class NodeNotFoundException extends CustomException {
    public NodeNotFoundException(){
        super(ErrorCode.NODE_NOT_FOUND);
    }
}
