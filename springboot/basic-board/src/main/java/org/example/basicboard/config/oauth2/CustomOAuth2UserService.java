package org.example.basicboard.config.oauth2;

import lombok.RequiredArgsConstructor;
import org.example.basicboard.domian.entity.Member;
import org.example.basicboard.domian.entity.Role; // 프로젝트 내 Role enum 경로에 맞춰 확인
import org.example.basicboard.domian.repository.MemberRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        AuthProvider provider = AuthProvider.from(registrationId);

        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        Map<String, Object> attributes = oAuth2User.getAttributes();
        OAuth2UserInfo userInfo = new KakaoUserInfo(attributes);

        // 카카오 식별자 ID 및 이메일
        String providerId = userInfo.id();
        String email = userInfo.email();
        String name = userInfo.name();

        // 기존 카카오 소셜 계정 조회 -> 없으면 이메일 기준 조회 -> 다 없으면 신규 생성 (소셜 자동 가입)
        Member member = memberRepository.findByProviderAndProviderId(provider, providerId)
                .orElseGet(() -> memberRepository.findByEmail(email)
                                   .map(existingMember -> {
                                       // 이미 일반가입 이메일이 있는 경우 소셜 연동 업데이트
                                       existingMember.updateSocialInfo(provider, providerId);
                                       return existingMember;
                                   })
                                   .orElseGet(() -> saveNewSocialMember(provider, providerId, email, name))
                          );

        return new CustomOAuth2User(
                member,
                provider,
                userInfo,
                attributes,
                userNameAttributeName
        );
    }

    private Member saveNewSocialMember(AuthProvider provider, String providerId, String email, String name) {
        Member newMember = Member.builder()
                .userId(provider.name().toLowerCase() + "_" + providerId) // 임의 식별 ID
                .email(email)
                .username(name != null ? name : "KakaoUser")
                .provider(provider)
                .providerId(providerId)
                .role(Role.ROLE_USER)
                .build();
        return memberRepository.save(newMember);
    }
}