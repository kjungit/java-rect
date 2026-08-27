package org.example.webservice.service;

import lombok.RequiredArgsConstructor;
import org.example.webservice.client.AuthClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthClient authClient;

}
