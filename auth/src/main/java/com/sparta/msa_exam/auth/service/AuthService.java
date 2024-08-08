package com.sparta.msa_exam.auth.service;

import com.sparta.msa_exam.auth.jwt.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtUtil jwtUtil;

    public AuthService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public String signIn(String userId) {
        return jwtUtil.createToken(userId);
    }
}
