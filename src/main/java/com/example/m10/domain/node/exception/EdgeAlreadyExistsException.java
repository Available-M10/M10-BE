package com.example.m10.domain.node.exception;

import com.example.m10.global.exception.model.CustomException;
import com.example.m10.global.exception.model.ErrorCode;

public class EdgeAlreadyExistsException extends CustomException {
    public EdgeAlreadyExistsException(){
        super(ErrorCode.EDGE_ALREADY_EXISTS);
    }
}
