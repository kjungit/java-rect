package org.example.jwt.service;

import lombok.RequiredArgsConstructor;
import org.example.jwt.config.security.CustomUserDetails;
import org.example.jwt.domain.entity.User;
import org.example.jwt.domain.repository.UserRepository;
import org.example.jwt.dto.SignInRequestDto;
import org.example.jwt.dto.SignInResponseDto;
import org.example.jwt.dto.SignUpRequestDto;
import org.example.jwt.exception.DuplicateUserIdException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional( readOnly = true )
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Transactional
    public void signUp( SignUpRequestDto requestDto ) {

        if ( userRepository.existsByUserId(requestDto.getUserId()) ) {
            throw new DuplicateUserIdException("[회원가입] 이미 사용중인 아이디입니다.");
        }

        User user = requestDto.toUser(passwordEncoder.encode(requestDto.getPassword()));

        userRepository.save(user);
    }

    public SignInResponseDto login( SignInRequestDto requestDto ) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getUserId(), requestDto.getPassword())
                                                                        );

        User user = ((CustomUserDetails) authenticate.getPrincipal()).getUser();

        TokenService.TokenPair tokenPair = tokenService.issueToken(user);

        return SignInResponseDto.builder()
                .isLoggedIn(true)
                .message("로그인 성공")
                .url("/")
                .accessToken(tokenPair.accessToken())
                .refreshToken(tokenPair.refreshToken())
                .userName(user.getName())
                .userId(user.getUserId())
                .build();
    }


}