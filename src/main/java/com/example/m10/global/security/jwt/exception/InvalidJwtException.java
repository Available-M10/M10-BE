package com.example.m10.global.security.jwt.exception;

import com.example.m10.global.model.CustomException;
import com.example.m10.global.model.ErrorCode;

public class InvalidJwtException extends CustomException {
    public InvalidJwtException(){
        super(ErrorCode.INVALID_JWT);
    }
}
