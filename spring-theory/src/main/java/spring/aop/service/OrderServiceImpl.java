package spring.aop.service;

public class OrderServiceImpl implements OrderService {
    @Override
    public String placeOrder( String item ) {
        sleep(80);
        return "주문 완료";
    }

    private void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {}
    }
}
