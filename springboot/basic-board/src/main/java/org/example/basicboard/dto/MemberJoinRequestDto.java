package org.example.basicboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.basicboard.domian.entity.Member;
import org.example.basicboard.domian.entity.Role;

@Getter
@Setter
@NoArgsConstructor
public class MemberJoinRequestDto {
    @Schema(description = "가입할 아이디", example = "user01")
    private String userId;

    @Schema(description = "비밀번호", example = "pass1234")
    private String password;

    @Schema(description = "표시할 사용자 이름", example = "홍길동")
    private String userName;

    @Schema(description = "표시할 사용자 역할", example = "ROLE_USER")
    private Role role;


    public Member toUser( String encodedPassword ) {
        return Member.builder()
                .userId(userId)
                .password(encodedPassword)
                .username(userName)
                .role(role != null ? role : Role.ROLE_USER)
                .build();
    }
}
