package org.example.authjpa.controller;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.authjpa.dto.LoginRequestDto;
import org.example.authjpa.dto.LoginResponseDto;
import org.example.authjpa.dto.MemberJoinRequestDto;
import org.example.authjpa.dto.MemberJoinResponseDto;
import org.example.authjpa.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/join")
    public MemberJoinResponseDto join( @RequestBody MemberJoinRequestDto dto) {
        memberService.join(dto);
        return new MemberJoinResponseDto("/members/login");
    }

    @PostMapping("/login")
    public LoginResponseDto login( @RequestBody LoginRequestDto dto, HttpSession session) {
        return memberService.login(dto)
                .map(
                        member -> {
                            session.setAttribute("userId", member.getUserId());
                            session.setAttribute("userName", member.getUsername());
                            return LoginResponseDto.success();
                        }
                ).orElseGet(LoginResponseDto::fail);
    }
}
