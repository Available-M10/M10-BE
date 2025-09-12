package com.example.m10.domain.node.exception;

import com.example.m10.global.exception.model.CustomException;
import com.example.m10.global.exception.model.ErrorCode;

public class NodeDeletionNotAllowedException extends CustomException {
    public NodeDeletionNotAllowedException(){
        super(ErrorCode.NODE_DELETION_NOT_ALLOWED);
    }
}
