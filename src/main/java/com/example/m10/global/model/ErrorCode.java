package com.example.m10.global.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러 입니다."),

    EXPIRED_JWT(HttpStatus.UNAUTHORIZED, "로그인 세션이 만료되었습니다."),
    INVALID_JWT(HttpStatus.UNAUTHORIZED, "유효하지 않는 토큰입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
