package com.sparta.msa_exam.auth.controller;

import com.sparta.msa_exam.auth.dto.AuthDto;
import com.sparta.msa_exam.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/signIn")
    public AuthDto signIn(@RequestParam("user_id") String userId) {
        return new AuthDto(authService.signIn(userId));
    }
}
