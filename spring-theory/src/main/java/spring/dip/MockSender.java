package spring.dip;

import java.util.ArrayList;
import java.util.List;

public class MockSender implements MessageSender {
    private final List<String> messages = new ArrayList<>();

    @Override
    public void send(String msg) {
        messages.add(msg);
    }

    public List<String> getMessages() {
        return messages;
    }
}
