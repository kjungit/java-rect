package org.example.basicboard.config.oauth2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.basicboard.config.jwt.JwtProperties;
import org.example.basicboard.config.jwt.TokenProvider;
import org.example.basicboard.config.oauth2.CustomOAuth2User;
import org.example.basicboard.domian.entity.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class KakaoOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        Member member = oAuth2User.getUser();

        String accessToken = tokenProvider.generateToken(member, jwtProperties.getAccessTokenValidity());
        String refreshToken = tokenProvider.generateToken(member, jwtProperties.getRefreshTokenValidity());
        String targetUrl = UriComponentsBuilder.fromUriString("/oauth2/redirect")
                .queryParam("accessToken", accessToken)
                .queryParam("refreshToken", refreshToken)
                .build().toUriString();

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}