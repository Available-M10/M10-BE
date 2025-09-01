package com.example.m10.domain.auth.service;

import com.example.m10.domain.auth.dto.request.LoginRequestDto;
import com.example.m10.domain.auth.dto.response.TokenResponseDto;
import com.example.m10.domain.auth.exception.PasswordMisMatchException;
import com.example.m10.domain.user.domain.User;
import com.example.m10.domain.user.domain.repository.UserRepository;
import com.example.m10.domain.user.exception.UserNotFoundException;
import com.example.m10.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional(readOnly = true)
    public TokenResponseDto login(LoginRequestDto dto){
        User user = userRepository.findByAccountId(dto.accountId())
                .orElseThrow(UserNotFoundException::new);

        if(!passwordEncoder.matches(dto.password(), user.getPassword())){
            throw new PasswordMisMatchException();
        }
        return TokenResponseDto.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(dto.accountId()))
                .refreshToken(jwtTokenProvider.generateRefreshToken(dto.accountId()))
                .build();
    }
}
