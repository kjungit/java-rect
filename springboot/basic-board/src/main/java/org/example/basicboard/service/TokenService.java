package org.example.basicboard.service;

import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.example.basicboard.config.jwt.JwtProperties;
import org.example.basicboard.config.jwt.TokenProvider;
import org.example.basicboard.config.jwt.TokenStatus;
import org.example.basicboard.domian.entity.Member;
import org.example.basicboard.dto.RefreshTokenResponseDto;
import org.example.basicboard.util.CookieUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;

    public record TokenPair(
            String accessToken,
            String refreshToken
    ) {
    }

    /**
     * Access Token + Refresh Token 발급
     * <p>
     * 일반 로그인과 OAuth2 로그인에서 공통으로 사용한다.
     */
    public TokenPair issueToken( Member user ) {

        String accessToken =
                tokenProvider.generateToken(
                        user,
                        jwtProperties.getAccessTokenValidity()
                                           );

        String refreshToken =
                tokenProvider.generateToken(
                        user,
                        jwtProperties.getRefreshTokenValidity()
                                           );

        return new TokenPair(
                accessToken,
                refreshToken
        );
    }

    /**
     * Refresh Token을 이용해서 Access Token과 Refresh Token을 재발급한다.
     */
    public RefreshTokenResponseDto refreshToken( Cookie[] cookies ) {

        String refreshToken = getRefreshToken(cookies);

        if (refreshToken != null
            && tokenProvider.validateToken(refreshToken)
               == TokenStatus.VALID) {

            Member user = tokenProvider.getTokenDetails(refreshToken);

            TokenPair tokenPair = issueToken(user);

            return RefreshTokenResponseDto.builder()
                    .validated(true)
                    .accessToken(tokenPair.accessToken())
                    .refreshToken(tokenPair.refreshToken())
                    .build();
        }

        return RefreshTokenResponseDto.builder()
                .validated(false)
                .build();
    }

    private String getRefreshToken( Cookie[] cookies ) {

        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName()
                    .equals(CookieUtil.REFRESH_TOKEN_COOKIE)) {
                return cookie.getValue();
            }
        }

        return null;
    }
}