package com.example.m10.domain.auth.presentation;

import com.example.m10.domain.auth.dto.request.LoginRequestDto;
import com.example.m10.domain.auth.dto.request.SignupRequestDto;
import com.example.m10.domain.auth.dto.response.TokenResponseDto;
import com.example.m10.domain.auth.service.LoginService;
import com.example.m10.domain.auth.service.ReissueService;
import com.example.m10.domain.auth.service.SignupService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final SignupService signupService;
    private final LoginService loginService;
    private final ReissueService reissueService;

    @PostMapping("/signup")
    public void signup(@Valid @RequestBody SignupRequestDto dto){
        signupService.signup(dto);
    }

    @PostMapping("/login")
    public TokenResponseDto login(@Valid @RequestBody LoginRequestDto dto){
        return loginService.login(dto);
    }

    @PostMapping("/reissue")
    public TokenResponseDto reissue(HttpServletRequest request){
        return reissueService.reissue(request);
    }
}
