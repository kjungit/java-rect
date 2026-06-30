package spring.abstract_decoration.decorater;

import spring.abstract_decoration.NotificationSender;

public class LoggingNotificationSender implements NotificationSender {
    private final NotificationSender delegate;

    public LoggingNotificationSender(NotificationSender delegate) {
        this.delegate = delegate;

    }

    @Override
    public void send(String to, String message) {
        System.out.println("[LOG] 발송 시작 : " + to);

        delegate.send(to, message);
        System.out.println("[LOG] 발송 완료 : " + to);

    }
}
