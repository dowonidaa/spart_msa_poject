package com.sparta.msa_exam.gateway.config;

import com.sparta.msa_exam.gateway.infrastructure.AuthClient;
import com.sparta.msa_exam.gateway.application.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {


    @Autowired
    AuthClient authClient;

    @Bean
    public AuthServiceImpl authService() {
        return new AuthServiceImpl(authClient);
    }
}
