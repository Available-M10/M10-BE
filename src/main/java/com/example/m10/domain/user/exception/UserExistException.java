package com.example.m10.domain.user.exception;

import com.example.m10.global.exception.model.CustomException;
import com.example.m10.global.exception.model.ErrorCode;

public class UserExistException extends CustomException {
    public UserExistException(){
        super(ErrorCode.USER_EXISTS);
    }
}
