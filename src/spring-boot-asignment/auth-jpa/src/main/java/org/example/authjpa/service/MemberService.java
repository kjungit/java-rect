package org.example.authjpa.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.authjpa.domain.entity.Member;
import org.example.authjpa.domain.repository.MemberRepository;
import org.example.authjpa.dto.LoginRequestDto;
import org.example.authjpa.dto.MemberJoinRequestDto;
import org.example.authjpa.exception.DuplicateUserIdException;
import org.example.authjpa.mapper.MemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Getter
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Transactional
    public void join( MemberJoinRequestDto dto ) {
        if (memberRepository.existsByUserId(dto.getUserId())) {
            throw new DuplicateUserIdException("[회원가입] 이미 존재하는 아이디입니다.");
        }
        memberRepository.save(memberMapper.toEntity(dto));
    }

    public Optional<Member> login( LoginRequestDto dto ) {
        return memberRepository.findByUserId(dto.getUsername())
                .filter(member -> member.getPassword().equals(dto.getPassword()));
    }
}
