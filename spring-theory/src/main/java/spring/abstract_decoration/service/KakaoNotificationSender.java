package spring.abstract_decoration.service;

import spring.abstract_decoration.NotificationSender;

public class KakaoNotificationSender implements NotificationSender {
    @Override
    public void send(String to, String message) {
        System.out.println("[KAKAO] to=" + to + ", message=" + message);
    }
}