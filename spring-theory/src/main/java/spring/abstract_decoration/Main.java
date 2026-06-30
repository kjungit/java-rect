package spring.abstract_decoration;

import spring.abstract_decoration.decorater.TimingNotificationSender;
import spring.abstract_decoration.service.EmailNotificationSender;
import spring.abstract_decoration.decorater.LoggingNotificationSender;
import spring.abstract_decoration.decorater.RetryNotificationSender;

public class Main {
    public static void main( String[] args ) {
//        NotificationSender sender = new EmailNotificationSender();
//        NotificationService service = new NotificationService(sender);
//
//        service.notifyUser("jun@test.com", "가입을 축하합니다");
//
//        NotificationSender smsSender = new SmsNotificationSender();
//        NotificationService smsService = new NotificationService(smsSender);
//
//        smsService.notifyUser("010-1234-5678", "문자 알림입니다.");
//
//        NotificationSender kakaoSender = new KakaoNotificationSender();
//        NotificationService kakaoService = new NotificationService(kakaoSender);
//
//        kakaoService.notifyUser("jun", "카카오 알림입니다.");

//        NotificationSender sender = new LoggingNotificationSender(new EmailNotificationSender());
//
//        NotificationService service = new NotificationService(sender);
//        service.notifyUser("jun@test.com", "가입 축하");


//        NotificationSender sender = new RetryNotificationSender(new FlakyEmailSender());
//        sender.send("jun@test.com", "hello");
//        NotificationSender sender = new TimingNotificationSender(new LoggingNotificationSender(new RetryNotificationSender(new EmailNotificationSender())));
//        NotificationService service = new NotificationService(sender);
//        service.notifyUser("jun@test.com", "회원가입을 축하합니다.");

        NotificationSender sender1 = new TimingNotificationSender(new LoggingNotificationSender(new RetryNotificationSender(new FlakyEmailSender())));
        NotificationSender sender2 = new TimingNotificationSender(new RetryNotificationSender(new LoggingNotificationSender(new FlakyEmailSender())));
        NotificationService service1 = new NotificationService(sender1);
        NotificationService service2 = new NotificationService(sender2);
        service1.notifyUser("jun1@test.com", "회원가입을 축하합니다.1");
        service1.notifyUser("jun2@test.com", "회원가입을 축하합니다.2");
    }
}

