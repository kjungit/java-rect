package org.example.basicboard.dto;

import lombok.Getter;
import org.example.basicboard.domian.entity.Member;
import org.example.basicboard.domian.entity.Role;
import org.springframework.security.core.userdetails.User;

@Getter
public class SignUpRequestDto {
    private String userId;
    private String password;
    private String username;
    private Role role;

    public Member toUser( String encodedPassword ) {
        return Member.builder()
                .userId(userId)
                .password(encodedPassword)
                .username(username)
                .role(role != null ? role : Role.ROLE_USER)
                .build();
    }
}
