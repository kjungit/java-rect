package spring.ioc_coffee;

public class EthiopiaBean implements CoffeeBean {

    @Override
    public String name() {
        return "에티오피아 원두";
    }
}
