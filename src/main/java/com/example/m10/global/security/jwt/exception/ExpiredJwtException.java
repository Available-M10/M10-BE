package com.example.m10.global.security.jwt.exception;

import com.example.m10.global.exception.model.CustomException;
import com.example.m10.global.exception.model.ErrorCode;

public class ExpiredJwtException extends CustomException {
    public ExpiredJwtException(){
        super(ErrorCode.EXPIRED_JWT);
    }
}
