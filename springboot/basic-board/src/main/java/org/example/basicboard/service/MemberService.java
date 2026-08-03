package org.example.basicboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.basicboard.config.security.CustomUserDetails;
import org.example.basicboard.domian.entity.Member;
import org.example.basicboard.domian.repository.MemberRepository;
import org.example.basicboard.dto.LoginRequestDto;
import org.example.basicboard.dto.MemberJoinRequestDto;
import org.example.basicboard.dto.SignInResponseDto;
import org.example.basicboard.exception.DuplicateUserIdException;
import org.example.basicboard.mapper.MemberMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private static final String KAKAO_USER_ID_PREFIX = "kakao_";
    private static final String OAUTH_PASSWORD_PREFIX = "{oauth2}";

    private static final String DEFAULT_KAKAO_USER_NAME = "카카오사용자";

    private static final int USER_ID_MAX_LENGTH = 50;
    private static final int USER_NAME_MAX_LENGTH = 20;

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Transactional
    public void signUp( MemberJoinRequestDto requestDto ) {

        if (memberRepository.existsByUserId(requestDto.getUserId())) {
            throw new DuplicateUserIdException("[회원가입] 이미 존재하는 아이디입니다.");
        }

        Member user = requestDto.toUser(passwordEncoder.encode(requestDto.getPassword()));

        memberRepository.save(user);
    }

    public SignInResponseDto login( LoginRequestDto requestDto ) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUserId(), requestDto.getPassword()));

        Member user = ((CustomUserDetails) authenticate.getPrincipal()).getUser();

        TokenService.TokenPair tokenPair = tokenService.issueToken(user);

        return SignInResponseDto.builder().isLoggedIn(true).message("로그인 성공").url("/").accessToken(tokenPair.accessToken()).refreshToken(tokenPair.refreshToken()).userName(user.getUsername()).userId(user.getUserId()).build();
    }
}