package com.example.m10.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${spring.ai.base-url}")
    private String ai_url;

    @Bean
    public WebClient llmWebClient(WebClient.Builder builder){
        return builder
                .baseUrl(ai_url)
                .build();
    }

}
