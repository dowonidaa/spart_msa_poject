package com.sparta.msa_exam.gateway.service;

import com.sparta.msa_exam.gateway.client.AuthClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthClient authClient;

    @Cacheable(cacheNames = "passport", key = "args[0]")
    public String verify(String username) {
        return authClient.verify(username);
    }

}
