package com.sparta.msa_exam.auth.application.mapper;

import com.sparta.msa_exam.auth.application.dto.SignUpDto;
import com.sparta.msa_exam.auth.domain.User;

public class UserMapper {

    public static User toEntity(SignUpDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

}
