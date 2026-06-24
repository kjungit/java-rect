package spring.singletone_2;

public class GreetingServiceBad {
    private static final GreetingServiceBad instance = new GreetingServiceBad();

    private GreetingServiceBad() {
    }

    static GreetingServiceBad getInstance() {
        return instance;
    }

    private String name;

    String greet(String reqName) {
        this.name = reqName;
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {

        }
        return name;
    }


}
