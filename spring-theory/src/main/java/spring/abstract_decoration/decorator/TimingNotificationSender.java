package spring.abstract_decoration.decorater;

import spring.abstract_decoration.NotificationSender;

public class TimingNotificationSender implements NotificationSender {

    private final NotificationSender delegate;

    public TimingNotificationSender(NotificationSender delegate) {
        this.delegate = delegate;
    }

    @Override
    public void send(String to, String message) {
        long start = System.nanoTime();
        delegate.send(to, message);
        long end = System.nanoTime();
        long elapsed = (end - start) / 1_000_000;

        System.out.println("[TIME] " + elapsed + "ms");
    }
}