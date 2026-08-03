package org.example.basicboard.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.basicboard.config.jwt.JwtProperties;
import org.example.basicboard.dto.*;
import org.example.basicboard.service.MemberService;
import org.example.basicboard.service.TokenService;
import org.example.basicboard.util.CookieUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;


@Tag(name = "회원 API", description = "회원 가입, 로그인, 로그아웃")

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApiController {
    private final MemberService memberService;
    private final JwtProperties jwtProperties;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/join")
    public MemberJoinResponseDto join( @RequestBody MemberJoinRequestDto requestDto ) {
        memberService.signUp(requestDto);
        return new MemberJoinResponseDto("/members/login");
    }

    @PostMapping("/login")
    public SignInResponseDto login(
            @RequestBody LoginRequestDto requestDto,
            HttpServletResponse response
                                  ) {
        SignInResponseDto signInResponseDto = memberService.login(requestDto);

        CookieUtil.addCookie(
                response,
                CookieUtil.REFRESH_TOKEN_COOKIE,
                signInResponseDto.getRefreshToken(),
                (int) jwtProperties.getRefreshTokenValidity().toSeconds()
                            );

        signInResponseDto.setRefreshToken(null);

        return signInResponseDto;

    }

    @PostMapping("/logout")
    public LogoutResponseDto logout(
            HttpServletRequest request,
            HttpServletResponse response
                                   ) {
        CookieUtil.deleteCookie(request, response, CookieUtil.REFRESH_TOKEN_COOKIE);
        return LogoutResponseDto.builder()
                .message("로그아웃 되었습니다.")
                .url("/members/login")
                .build();
    }

}
