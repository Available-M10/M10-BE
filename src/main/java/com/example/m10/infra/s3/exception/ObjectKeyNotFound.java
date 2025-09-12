package com.example.m10.infra.s3.exception;

import com.example.m10.global.exception.model.CustomException;
import com.example.m10.global.exception.model.ErrorCode;

public class ObjectKeyNotFound extends CustomException {
    public ObjectKeyNotFound(){
        super(ErrorCode.OBJECT_KEY_NOT_FOUND);
    }
}
