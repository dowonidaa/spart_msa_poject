package com.sparta.msa_exam.auth.mapper;

import com.sparta.msa_exam.auth.dto.SignUpDto;
import com.sparta.msa_exam.auth.entity.User;

public class UserMapper {

    public static User toEntity(SignUpDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

}
