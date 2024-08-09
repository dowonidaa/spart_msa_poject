package com.sparta.msa_exam.auth.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {

    private String username;
    private String password;
    private String email;
}
