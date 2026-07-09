package org.example.authjpa.domain.repository;

import org.example.authjpa.domain.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        Member member = Member.builder()
                .userId("test")
                .password("1234")
                .username("testName")
                .build();

        memberRepository.save(member);
    }

    @Test
    @DisplayName("existsByUserId - 존재하는 아이디면 true를 반환한다.")
    void existsByUserId_존재하면_true() {
        // when
        boolean exists = memberRepository.existsByUserId("test");

        //then
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("existsByUserId - 존재하지 않는 아이디면 false를 반환한다.")
    void existsByUserId_존재하지않으면_false() {
        // when
        boolean exists = memberRepository.existsByUserId("nono");

        //then
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("findByUserId - 존재하는 아이디로 조회하면 회원이 담김 Optional을 반환한다.")
    void findByUserId_존재하면_회원() {
        Optional<Member> found = memberRepository.findByUserId("test");

        assertThat(found).isPresent();

        assertThat(found.get().getUsername()).isEqualTo("testName");
    }

    @Test
    @DisplayName("findByUserId - 존재하지 않는 아이디로 조회하면 비어있는 Optional을 반환한다.")
    void findByUserId_존재하지_않으면_빈_Optional() {
        Optional<Member> found = memberRepository.findByUserId("nono");

        assertThat(found).isEmpty();

    }
}