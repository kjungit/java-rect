package org.example.authjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {
    private boolean succeeded;
    private String url;
    private String message;

    public static LoginResponseDto success() {
        return new LoginResponseDto(true, "/", "로그인 성공");
    }

    public static LoginResponseDto fail() {
        return new LoginResponseDto(false, null, "아이디 또는 비밀번호가 일치하지 않음");
    }
}
