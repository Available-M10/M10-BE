package com.example.m10.domain.node.exception;

import com.example.m10.global.exception.model.CustomException;
import com.example.m10.global.exception.model.ErrorCode;

public class NodeConnectionException extends CustomException {
    public NodeConnectionException(){
        super(ErrorCode.EDGE_LINK_FAILED);
    }
}
