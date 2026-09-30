package org.example.authservice.config.oauth2;


//   {
//     "id": 123456789,                          ← 회원번호(숫자). 유일하게 최상위에 있다
//     "kakao_account": {
//       "email": "user@example.com",            ← 동의 항목(비즈 앱)에 따라 아예 없을 수 있음
//       "profile": {
//         "nickname": "홍길동",
//         "profile_image_url": "https://..."
//       }
//     }
//   }

import java.util.Map;

public record KakaoUserInfo(
        Map<String, Object> attributes
) implements OAuth2UserInfo {
    @Override
    public String id() {
        Object id = attributes.get("id");
        return id == null ? null : String.valueOf(id);
    }

    @Override
    public String email() {
        return asString(kakaoAccount().get("email"));
    }

    @Override
    public String name() {
        return asString(profile().get("nickname"));
    }

    @Override
    public String imageUrl() {
        return asString(profile().get("profile_image_url"));
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> kakaoAccount() {
        Object account = attributes.get("kakao_account");
        return account instanceof Map ? (Map<String, Object>) account : Map.of();
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> profile() {
        Object profile = kakaoAccount().get("profile");
        return profile instanceof Map ? (Map<String, Object>) profile : Map.of();
    }

    // String.valueOf(null)은 "null" 문자열이 되므로 직접 처리
    private static String asString(Object value) {
        return value == null ? null : String.valueOf(value);
    }

}