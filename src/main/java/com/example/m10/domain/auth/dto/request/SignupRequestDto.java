package com.example.m10.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequestDto(
        @NotBlank(message = "아이디 입력을 해주세요.")
        @Size(min = 3, max = 10, message = "아이디는 3글자 이상 10글자 이하로 입력해주세요.")
        String accountId,

        @NotBlank(message = "비밀번호를 입력해주세요")
        @Size(min = 6, max = 20, message = "비밀번호는 6글자 이상 20글자 이하로 입력해주세요.")
        String password
) {
}
