package spring.abstract_decoration.service;

import spring.abstract_decoration.NotificationSender;

public class SmsNotificationSender implements NotificationSender {
    @Override
    public void send(String to, String message) {
        System.out.println("[SMS] to=" + to + ", message=" + message);
    }
}