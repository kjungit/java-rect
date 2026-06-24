package spring.solid.ocp;

public class Main {

    public static void main(String[] args) {
        int price = 10000;

        DiscountPolicy basic = new BasicDiscount();
        DiscountPolicy silver = new SilverDiscount();
        DiscountPolicy gold = new GoldDiscount();
        DiscountPolicy vip = new VipDiscount();

        System.out.println(basic.discount(price));
        System.out.println(silver.discount(price));
        System.out.println(gold.discount(price));
        System.out.println(vip.discount(price));
    }

}
