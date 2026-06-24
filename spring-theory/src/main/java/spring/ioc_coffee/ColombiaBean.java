package spring.ioc_coffee;

public class ColombiaBean implements CoffeeBean {

    @Override
    public String name() {
        return "콜롬비아 원두";
    }

}
