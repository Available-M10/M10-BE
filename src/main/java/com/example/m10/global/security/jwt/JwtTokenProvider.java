package com.example.m10.global.security.jwt;

import com.example.m10.domain.auth.domain.RefreshToken;
import com.example.m10.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.m10.domain.user.domain.User;
import com.example.m10.domain.user.domain.repository.UserRepository;
import com.example.m10.global.security.auth.AuthDetails;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private final JwtTokenStructure jwtTokenStructure;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private static final String ACCESS_TOKEN = "access_token";
    private static final String REFRESH_TOKEN = "refresh_token";

    public String generateAccessToken(String accountId){
        return jwtTokenStructure.generateToken(
                accountId,
                ACCESS_TOKEN,
                jwtProperties.accessExp());
    }

    public String generateRefreshToken(String accountId){
        String refreshToken = jwtTokenStructure.generateToken(
                accountId,
                REFRESH_TOKEN,
                jwtProperties.refreshExp());

        refreshTokenRepository.save(RefreshToken.builder()
                .accountId(accountId)
                .refreshToken(refreshToken)
                .ttl(jwtProperties.refreshExp())
                .build());
        return refreshToken;
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(jwtProperties.header());
        return jwtTokenStructure.parseToken(bearerToken);
    }


    public Authentication getAuthentication(String token){
        String accountId = jwtTokenStructure.getTokenSubject(token);
        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(() -> new UsernameNotFoundException("인증 실패"));

        UserDetails userDetails = new AuthDetails(user);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
