package com.sparta.msa_exam.gateway.service;

import org.springframework.cache.annotation.Cacheable;

public interface AuthService {

    @Cacheable(cacheNames = "passport", key = "args[0]")
    String verify(String name);
}
