package org.example.jwt.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LogoutResponseDto {
    String message;
    String url;
}