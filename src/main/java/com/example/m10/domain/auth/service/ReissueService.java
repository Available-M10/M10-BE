package com.example.m10.domain.auth.service;

import com.example.m10.domain.auth.domain.RefreshToken;
import com.example.m10.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.m10.domain.auth.dto.response.TokenResponseDto;
import com.example.m10.domain.auth.exception.InvalidRefreshTokenException;
import com.example.m10.domain.auth.exception.RefreshTokenNotFoundException;
import com.example.m10.global.security.jwt.JwtProperties;
import com.example.m10.global.security.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReissueService {
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public TokenResponseDto reissue(HttpServletRequest request){
        String parseToken = jwtTokenProvider.resolveToken(request);

        if(parseToken == null){
            throw new InvalidRefreshTokenException();
        }

        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(parseToken)
                .orElseThrow(RefreshTokenNotFoundException::new);

        String accountId = refreshToken.getAccountId();
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(accountId);
        refreshToken.update(newRefreshToken, jwtProperties.refreshExp());


        return TokenResponseDto.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(accountId))
                .refreshToken(newRefreshToken)
                .build();
    }
}
