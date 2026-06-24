package spring.ioc_coffee;

public class CoffeeMaker {
    private final CoffeeBean bean;

    public CoffeeMaker(CoffeeBean bean) {
        this.bean = bean;
    }

    public void brew() {
        System.out.println(bean.name() + "커피 내림");
    }
}