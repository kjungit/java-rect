package spring.abstract_decoration.service;

import spring.abstract_decoration.NotificationSender;

public class EmailNotificationSender implements NotificationSender {
    @Override
    public void send(String to, String message) {
        System.out.printf("[EMAIL] to=%s : %s%n", to, message);
    }
}