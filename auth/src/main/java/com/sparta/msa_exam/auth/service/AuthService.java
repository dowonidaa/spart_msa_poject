package com.sparta.msa_exam.auth.service;

import com.sparta.msa_exam.auth.dto.SignUpDto;
import com.sparta.msa_exam.auth.entity.User;
import com.sparta.msa_exam.auth.jwt.JwtUtil;
import com.sparta.msa_exam.auth.mapper.UserMapper;
import com.sparta.msa_exam.auth.repo.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String signUp(SignUpDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        passwordEncoder(dto);
        User user = userRepository.save(UserMapper.toEntity(dto));
        return dto.getUsername();

    }


    private void passwordEncoder(SignUpDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
    }

    public String verification(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        return user.getUsername();
    }
}
