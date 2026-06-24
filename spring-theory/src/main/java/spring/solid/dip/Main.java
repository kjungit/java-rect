package spring.solid.dip;

public class Main {
    public static void main(String[] args) {

        MockSender mockSender = new MockSender();
        NotificationService mockService =
                new NotificationService(mockSender);

        NotificationService emailService = new NotificationService(new EmailSender());
        NotificationService smsService = new NotificationService(new SmsSender());

        emailService.notifyUser("안녕");
        smsService.notifyUser("안녕");

        mockService.notifyUser("테스트1");
        mockService.notifyUser("테스트2");
        mockService.notifyUser("테스트3");

        System.out.println(mockSender.getMessages());


    }
}
