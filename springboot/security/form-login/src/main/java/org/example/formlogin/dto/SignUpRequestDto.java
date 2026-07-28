package org.example.formlogin.dto;

import lombok.Getter;
import org.example.formlogin.domain.entity.User;

@Getter
public class SignUpRequestDto {
    private String userId;
    private String password;
    private String username;

    public User toUser(String encodedPassword) {
        return User.builder()
                .userId(userId)
                .password(encodedPassword)
                .name(username)
                .build();
    }
}
