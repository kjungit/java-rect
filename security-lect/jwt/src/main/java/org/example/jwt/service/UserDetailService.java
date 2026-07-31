package org.example.jwt.service;

import lombok.RequiredArgsConstructor;
import org.example.jwt.config.security.CustomUserDetails;
import org.example.jwt.domain.entity.User;
import org.example.jwt.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

        return CustomUserDetails.builder()
                .user(user)
                .build();
    }
}
