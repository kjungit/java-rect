package spring.solid.ocp;

public class BasicDiscount implements DiscountPolicy {
    @Override
    public int discount(int price) {
        return price;
    }
}
