package com.asessment.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebCliConfig {
    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}
