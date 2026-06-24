package spring.singletone_2;

public class Main {
    static int badMismatch = 0;
    static int goodMismatch = 0;

    public static void main(String[] args) throws InterruptedException {
//        GreetingServiceBad service = GreetingServiceBad.getInstance();
//
//        for (int i = 0; i < 100; i++) {
//            final String name = String.valueOf(i);
//            new Thread(() -> {
//                String result = service.greet(name);
//                System.out.println();
//                if (!result.equals(name)) {
//                    System.out.println("❌ mismatch: " + name + " -> " + result);
//                }
//            }).start();
//
//        }

//        GreetingServiceGood goodService = GreetingServiceGood.getInstance();
//
//        for (int i = 0; i < 100; i++) {
//            final String name = String.valueOf(i);
//
//            new Thread(() -> {
//                String result = goodService.greet(name);
//                System.out.println(result);
//                if (!result.equals(name)) {
//                    System.out.println("❌ mismatch: " + name + " -> " + result);
//                }
//            }).start();
//        }


        UserDao dao = UserDao.getInstance();
        for (int i = 0; i < 100; i++) {
            final String userId = String.valueOf(i);
            new Thread(() -> {
                String result = dao.findUser(userId);
                if (!result.contains(userId)) {
                    System.out.println("❌ mismatch: " + userId + " -> " + result);
                } else {
                    System.out.println("✅ " + result);
                }

            }).start();

        }
    }
}
