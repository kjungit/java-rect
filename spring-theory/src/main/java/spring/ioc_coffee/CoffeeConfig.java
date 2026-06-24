package spring.ioc_coffee;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoffeeConfig {
    @Bean
    public CoffeeBean colombiaBean() {
        return new ColombiaBean();
    }

    @Bean
    public CoffeeBean ethiopiaBean() {
        return new EthiopiaBean();
    }

    @Bean
    public CoffeeMaker coffeeMaker() {
        return new CoffeeMaker(colombiaBean());
    }


}
