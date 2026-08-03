package org.example.basicboard.config.oauth2;

public enum AuthProvider {
    LOCAL,
    KAKAO;

    public static AuthProvider from(String registrationId) {
        return AuthProvider.valueOf(registrationId.toUpperCase());
    }
}
