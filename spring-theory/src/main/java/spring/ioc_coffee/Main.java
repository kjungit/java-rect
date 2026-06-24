package spring.ioc_coffee;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CoffeeConfig.class);
//        CoffeeMaker maker = context.getBean(CoffeeMaker.class);
//
//        maker.brew();

        CoffeeBean colombiaBean = new ColombiaBean();
        CoffeeMaker colombiaMaker = new CoffeeMaker(colombiaBean);

        colombiaMaker.brew();


        CoffeeContainer coffeeContainer = new CoffeeContainer();
        CoffeeMaker maker = coffeeContainer.getCoffeeMaker();
        maker.brew();


        Button button = new Button();

        LikeAction likeAction = new LikeAction();
        button.setListener(likeAction);
        button.press();
    }
}
