package spring.stream_java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>(Arrays.asList(
                new Product("연필", 500), new Product("공책", 1200), new Product("지우개", 300),
                new Product("필통", 3000), new Product("볼펜", 800)
        ));


        List<Order> orders = Arrays.asList(
                new Order(1, Arrays.asList("연필", "공책")),
                new Order(2, Arrays.asList("필통", "볼펜", "공책"))
        );

        List<List<String>> byMap = orders.stream()
                .map(o -> o.getItems())
                .collect(Collectors.toList());


        List<String> byFlatMap = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.toList());

        List<String> ex = products.stream()
                .filter(i -> i.getPrice() >= 1000)
                .map(i -> i.getName())
                .collect(Collectors.toList());

        Double ever = products.stream()
                .mapToInt(i -> i.getPrice())
                .average().getAsDouble();

        List<String> sorted = products.stream()
                .sorted((a, b) -> b.getPrice() - a.getPrice())
                .map(i -> i.getName())
                .collect(Collectors.toList());

        Product expensiveProduct = products.stream()
                .sorted((a, b) -> b.getPrice() - a.getPrice())
                .limit(1)
                .findFirst()
                .get();


        List<String> res = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        double averagePrice = products.stream()
                .collect(Collectors.averagingInt(Product::getPrice));

        Map<String, List<Product>> grouped = products.stream()
                .collect(Collectors.groupingBy(product ->
                        product.getPrice() < 1000 ? "1000원 미만" : "1000원 이상"
                ));

        int sum = products.stream().map(Product::getPrice).reduce(0, (s, p) -> s + p);


        System.out.println(sum);
    }
}
