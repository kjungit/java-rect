package spring.solid.dip;

public class NotificationService {
    private final MessageSender sender;

    public NotificationService(MessageSender sender) {
        this.sender = sender;
    }

    public void notifyUser(String msg) {
        sender.send(msg);
    }
}
