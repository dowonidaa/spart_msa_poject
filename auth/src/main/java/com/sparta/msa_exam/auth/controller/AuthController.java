package com.sparta.msa_exam.auth.controller;

import com.sparta.msa_exam.auth.dto.SignUpDto;
import com.sparta.msa_exam.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto dto) {
        return ResponseEntity.ok(authService.signUp(dto));
    }

    @PostMapping("/verification")
    public String verify(String username) {
        return authService.verification(username);
    }



}
