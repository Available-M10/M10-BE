package com.example.m10.global.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.jwt")
public record JwtProperties(
        String secretKey,
        Long accessExp,
        Long refreshExp,
        String header,
        String prefix
) {
}
