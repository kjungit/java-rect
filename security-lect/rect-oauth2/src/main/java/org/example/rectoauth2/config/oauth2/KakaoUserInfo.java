package org.example.rectoauth2.config.oauth2;

import java.util.Map;


public record KakaoUserInfo(
        Map<String, Object> attributes
) implements OAuth2UserInfo {
    @Override
    public String id() {
        Object id = attributes.get("id");
        return id == null ? null : id.toString();
    }

    @Override
    public String email() {
        Map<String, Object> kakaoAccount = kakaoAccount();
        return kakaoAccount == null ? null : String.valueOf( kakaoAccount.get("email") );
    }

    @Override
    public String name() {
        Map<String, Object> profile = profile();
        return profile == null ? null : String.valueOf( profile.get("nickname") );
    }

    @Override
    public String imageUrl() {
        return "";
    }


    private Map<String, Object> kakaoAccount() {
        return (Map<String, Object>) attributes.get("kakao_account");
    }

    private Map<String, Object> profile() {
        return (Map<String, Object>) kakaoAccount().get("profile");
    }

}
