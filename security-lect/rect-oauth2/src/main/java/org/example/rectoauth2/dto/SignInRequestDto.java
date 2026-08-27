package org.example.rectoauth2.dto;

import lombok.Getter;

@Getter
public class SignInRequestDto {
    private String userId;
    private String password;
}