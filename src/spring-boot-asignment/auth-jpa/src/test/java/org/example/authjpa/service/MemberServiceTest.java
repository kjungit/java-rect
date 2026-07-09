package org.example.authjpa.service;

import org.example.authjpa.domain.entity.Member;
import org.example.authjpa.domain.repository.MemberRepository;
import org.example.authjpa.dto.LoginRequestDto;
import org.example.authjpa.dto.MemberJoinRequestDto;
import org.example.authjpa.exception.DuplicateUserIdException;
import org.example.authjpa.mapper.MemberMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    private Member member;
    private MemberJoinRequestDto joinRequestDto;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MemberMapper memberMapper;

    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        member = createMember();
        joinRequestDto = createJoinRequest();
    }

    @Test
    @DisplayName("로그인 - 아이디가 있고 비밀번호가 일치하면 회원을 담은 Optional을 반환한다")
    void login_아이디와_비밀번호가_맞으면_회원을_반환() {
        given(memberRepository.findByUserId("test"))
                .willReturn(Optional.of(member));

        LoginRequestDto requestDto = createLoginRequest("test", "1234");
        Optional<Member> result = memberService.login(requestDto);
        assertThat(result).isPresent();

        assertThat(result.get().getUsername()).isEqualTo("홍길동");
    }



    @Test
    @DisplayName("로그인 - 비밀번호가 틀리면 빈 Optional을 반환한다.")
    void login_비밀번호가_틀리면_빈_Optional() {
        given(memberRepository.findByUserId("test"))
                .willReturn(Optional.of(member));
        LoginRequestDto requestDto = createLoginRequest("test", "9999");
        Optional<Member> result = memberService.login(requestDto);

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("로그인 - 아이디가 없으면 빈 Optional을 반환한다.")
    void login_아이디가_없으면_빈_Optional() {
        given(memberRepository.findByUserId("nobody"))
                .willReturn(Optional.empty());
        LoginRequestDto requestDto = createLoginRequest("nobody", "9999");
        Optional<Member> result = memberService.login(requestDto);

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("회원가입 - 아이디가 중복이 아니면 회원을 저장한다.")
    void join_중복이_아니면_저장한다() {
        given(memberRepository.existsByUserId("test"))
                .willReturn(false);
        given(memberMapper.toEntity(joinRequestDto))
                .willReturn(member);
        memberService.join(joinRequestDto);

        verify(memberRepository).save(member);
    }

    @Test
    @DisplayName("회원가입 - 아이디가 이미 있으면 DuplicateUserIdException을 던지고 저장하지 않는다.")
    void join_중복이면_예외() {
        given(memberRepository.existsByUserId("test"))
                .willReturn(true);
        assertThatThrownBy(() -> memberService.join(joinRequestDto))
                .isInstanceOf(DuplicateUserIdException.class)
                .hasMessageContaining("[회원가입] 이미 존재하는 아이디입니다.");

        verify(memberRepository, never()).save(any());
    }


    private Member createMember() {
        return Member.builder().userId("test").password("1234").username("홍길동").build();
    }

    private LoginRequestDto createLoginRequest( String userId, String password ) {
        LoginRequestDto dto = new LoginRequestDto();
        dto.setUsername(userId);
        dto.setPassword(password);
        return dto;
    }

    private MemberJoinRequestDto createJoinRequest() {
        MemberJoinRequestDto dto = new MemberJoinRequestDto();
        dto.setUserId("test");
        dto.setPassword("1234");
        dto.setUserName("홍길동");
        return dto;
    }
}