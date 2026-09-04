package org.example.authservice.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.authservice.config.jwt.JwtProperties;
import org.example.authservice.config.security.CustomUserDetails;
import org.example.authservice.domian.entity.User;
import org.example.authservice.dto.*;
import org.example.authservice.service.UserService;
import org.example.authservice.util.CookieUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserApiController {

    private final UserService userService;
    private final JwtProperties jwtProperties;

    @PostMapping("/join")
    public SignUpResponseDto join(@RequestBody SignUpRequestDto signUpRequestDto) {

        userService.signUp(signUpRequestDto);

        return SignUpResponseDto.builder()
                .url("/users/login")
                .build();
    }

    @PostMapping("/login")
    public SignInResponseDto login(
            @RequestBody SignInRequestDto signInRequestDto,
            HttpServletResponse response
                                  ) {

        SignInResponseDto logined = userService.login(signInRequestDto);

        CookieUtil.addCookie(
                response,
                CookieUtil.REFRESH_TOKEN_COOKIE,
                logined.getRefreshToken(),
                (int) jwtProperties.getRefreshTokenValidity().toSeconds()
                            );

        logined.setRefreshToken(null);

        return logined;
    }

    @GetMapping("/info")
    public UserInfoResponseDto getUserInfo(@AuthenticationPrincipal CustomUserDetails userDetails ) {
        User user = userDetails.getUser();
        return UserInfoResponseDto.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .userName(user.getName())
                .role(user.getRole())
                .build();
    }

    @GetMapping("/names")
    public List<UserNameResponseDto> getUserNames( @RequestParam List<String> userIds ) {
        return userService.getUserNames(userIds);
    }
}
