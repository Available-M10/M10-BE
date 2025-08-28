package com.example.m10.global.security.jwt.exception;

import com.example.m10.global.exception.model.CustomException;
import com.example.m10.global.exception.model.ErrorCode;

public class InvalidJwtException extends CustomException {
    public InvalidJwtException(){
        super(ErrorCode.INVALID_JWT);
    }
}
