package spring.abstract_decoration.decorator;

import spring.abstract_decoration.NotificationSender;

public class RetryNotificationSender implements NotificationSender {
    private final NotificationSender delegate;

    public RetryNotificationSender(NotificationSender delegate) {
        this.delegate = delegate;
    }

    @Override
    public void send(String to, String message) {
        int maxRetry = 3;
        Exception lastException = null;
        for (int i = 1; i <= maxRetry; i++) {
            try {
                delegate.send(to, message);
                return; // 성공하면 종료
            } catch (Exception e) {
                lastException = e;
                System.out.println("[RETRY] 실패 " + i + "회");
            }
        }

        throw new RuntimeException("3회 재시도 후 발송 실패", lastException);
    }
}
