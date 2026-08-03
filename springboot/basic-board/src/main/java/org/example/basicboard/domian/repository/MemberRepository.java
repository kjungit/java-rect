package org.example.basicboard.domian.repository;


import org.example.basicboard.domian.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.example.basicboard.config.oauth2.AuthProvider;
import java.util.Optional;



public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByUserId(String userId);

    Optional<Member> findByUserId(String userId);

    // [추가] 소셜 로그인 제공자와 소셜 ID로 회원 조회
    Optional<Member> findByProviderAndProviderId(AuthProvider provider, String providerId);

    // [추가] 이메일로 기존 회원 조회 (소셜 계정 연동 및 자동가입 체크용)
    Optional<Member> findByEmail(String email);
}