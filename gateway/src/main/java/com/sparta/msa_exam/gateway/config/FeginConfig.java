package com.sparta.msa_exam.gateway.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeginConfig {

        @Bean
        public HttpMessageConverters messageConverters() {
            return new HttpMessageConverters();
        }
    }

