package spring.ocp;

public class SilverDiscount implements DiscountPolicy{
    @Override
    public int discount(int price) {
        return price * 85 / 100;
    }
}
