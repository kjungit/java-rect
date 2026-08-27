package org.example.rectoauth2.domain.repository;


import org.example.rectoauth2.config.oauth2.AuthProvider;
import org.example.rectoauth2.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId( String userId );

    boolean existsByUserId(String userId);

    Optional<User> findByProviderIdAndProvider(String providerId, AuthProvider provider );
}