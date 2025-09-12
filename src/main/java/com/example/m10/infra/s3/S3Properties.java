package com.example.m10.infra.s3;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cloud.aws")
public record S3Properties(
        String bucket,
        String accessKey,
        String secretKey,
        String region
) {
}
