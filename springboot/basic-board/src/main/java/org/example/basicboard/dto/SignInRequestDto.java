package org.example.basicboard.dto;

import lombok.Getter;

@Getter
public class SignInRequestDto {
    private String userId;
    private String password;
}