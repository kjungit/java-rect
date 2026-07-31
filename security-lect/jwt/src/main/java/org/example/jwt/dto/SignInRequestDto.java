package org.example.jwt.dto;

import lombok.Getter;

@Getter
public class SignInRequestDto {
    private String userId;
    private String password;
}