package spring.ioc_coffee;

public class CoffeeContainer {
    public CoffeeMaker getCoffeeMaker() {
        CoffeeBean coffeeBean = new ColombiaBean();
        return new CoffeeMaker(coffeeBean);
    }
}
