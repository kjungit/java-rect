package org.example.authjpa.mapper;

import org.example.authjpa.domain.entity.Member;
import org.example.authjpa.dto.MemberJoinRequestDto;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public Member toEntity( MemberJoinRequestDto dto ) {
        return Member.builder()
                .userId( dto.getUserId() )
                .password(dto.getPassword())
                .username(dto.getUserName())
                .build();
    }
}
