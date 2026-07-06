package org.example.basicboard.mapper;

import org.example.basicboard.domian.entity.Member;
import org.example.basicboard.dto.MemberJoinRequestDto;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public Member toEntity( MemberJoinRequestDto dto ) {
        return Member.builder()
                .userId(dto.getUserId())
                .password(dto.getPassword())
                .username(dto.getUserName())
                .build();
    }
}
