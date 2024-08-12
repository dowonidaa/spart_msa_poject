package com.sparta.msa_exam.gateway.application;

import com.sparta.msa_exam.gateway.infrastructure.AuthClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final AuthClient authClient;


    public String verify(String username) {
        return authClient.verify(username);
    }

}
