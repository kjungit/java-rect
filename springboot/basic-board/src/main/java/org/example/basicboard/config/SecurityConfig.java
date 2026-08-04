package org.example.basicboard.config;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.basicboard.config.filter.TokenAuthenticationFilter;
import org.example.basicboard.config.oauth2.CustomOAuth2UserService;
import org.example.basicboard.config.oauth2.KakaoOAuth2SuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final KakaoOAuth2SuccessHandler kakaoOAuth2SuccessHandler;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
                                                  ) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                                               .requestMatchers(
                                                       "/",
                                                       "/oauth2/redirect",
                                                       "/write",
                                                       "/detail",
                                                       "/update",
                                                       "/stats",
                                                       "/boards/stats",
                                                       "/api/boards/stats/authors",
                                                       "/members/join",
                                                       "/members/login",
                                                       "/api/members/join",
                                                       "/api/members/login",
                                                       "/api/boards/**",
                                                       "/with-comments",
                                                       "/css/**",
                                                       "/js/**",
                                                       "/favicon.ico",
                                                       "/error",
                                                       "/swagger-ui.html",
                                                       "/swagger-ui/**",
                                                       "/v3/api-docs/**",
                                                       "/oauth2/**",
                                                       "/login/oauth2/**"
                                                               ).permitAll()
                                               .anyRequest().authenticated()
                                      )
                .oauth2Login(oauth2 -> oauth2
                                                            .userInfoEndpoint(userInfo -> userInfo
                                                                                      .userService(customOAuth2UserService)
                                                                             )
                                                            .successHandler(kakaoOAuth2SuccessHandler)
                                                   )

                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception
                                           .accessDeniedHandler(accessDeniedHandler())
                                           .authenticationEntryPoint(authenticationEntryPoint())
                                  );

        return http.build();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration authenticationConfiguration ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.withDefaultRolePrefix() // "ROLE_" 접두사 자동 부착
                .role("ADMIN").implies("USER")
                .build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (
                ( request, response, accessDeniedException ) -> {
                    if (request.getRequestURI().startsWith("/api")) {
                        sendError(response, HttpServletResponse.SC_FORBIDDEN, "접근 권한이 없습니다.");
                    } else {
                        response.sendRedirect("/access-denied");
                    }
                }
        );
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (
                ( request, response, authException ) -> {
                    if (request.getRequestURI().startsWith("/api")) {
                        sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "인증이 필요합니다.");
                    } else {
                        response.sendRedirect("/access-denied");
                    }
                }
        );
    }

    private void sendError( HttpServletResponse response, int status, String message ) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("status code : " + status + ", message : " + message);
    }
}
