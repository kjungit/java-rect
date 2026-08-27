package org.example.webservice.client;

import org.example.webservice.dto.SignUpRequestDto;
import org.example.webservice.dto.SignUpResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "auth-service", url = "${edge-service.url:http://localhost:8000}")
public interface AuthClient {
    @PostMapping("/api/users/join")
    SignUpResponseDto join( @RequestBody SignUpRequestDto signUpRequestDto );
}
