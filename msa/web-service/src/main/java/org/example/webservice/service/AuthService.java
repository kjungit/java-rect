package org.example.webservice.service;

import lombok.RequiredArgsConstructor;
import org.example.webservice.client.AuthClient;
import org.example.webservice.dto.SignUpRequestDto;
import org.example.webservice.dto.SignUpResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthClient authClient;

    public SignUpResponseDto signUp( @RequestBody SignUpRequestDto signUpRequestDto ) {
        return authClient.join(signUpRequestDto);
    }

}
