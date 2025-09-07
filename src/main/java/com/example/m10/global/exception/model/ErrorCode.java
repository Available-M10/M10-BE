package com.example.m10.global.exception.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류 입니다."),

    EXPIRED_JWT(HttpStatus.UNAUTHORIZED, "로그인 세션이 만료되었습니다."),
    INVALID_JWT(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "리프레시 토큰이 유효하지 않습니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "리프레시 토큰이 존재하지 않습니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저가 존재하지 않습니다."),
    USER_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 유저입니다."),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "비밀번호가 다릅니다."),

    PROJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 프로젝트가 존재하지 않습니다."),

    FORBIDDEN_PROJECT_UPDATE(HttpStatus.FORBIDDEN, "프로젝트를 수정할 권한이 없습니다."); // 권한 관련된거라 빼놨느데 프젝으로 생각하며 붙여도 됨

    private final HttpStatus httpStatus;
    private final String message;
}
