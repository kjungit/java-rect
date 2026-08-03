package org.example.basicboard.service;

import lombok.RequiredArgsConstructor;
import org.example.basicboard.config.security.CustomUserDetails;
import org.example.basicboard.domian.entity.Member;
import org.example.basicboard.domian.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final MemberRepository userRepository;


    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member user = userRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

        return CustomUserDetails.builder()
                .user(user)
                .build();
    }
}

