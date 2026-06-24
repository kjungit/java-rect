package spring.solid.dip;

public class EmailSender implements MessageSender {

    @Override
    public void send(String msg) {
        System.out.println("[이메일] : " + msg);
    }
}
