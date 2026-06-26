package spring.lambda_func;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        Operation add = new Operation() {
//            @Override
//            public int apply(int a, int b) {
//                return a + b;
//            }
//        };
//        Operation addAnon   = new Operation() {
//            @Override public int apply(int a, int b) { return a + b; }
//        };
//        Operation addLambda = (a, b) -> a + b;
//
//        Operation add1 = (int a, int b) -> { return a + b; };
//        Operation add2 = (a, b) -> { return a + b; };
//        Operation add3 = (a, b) -> a + b;
//        Operation sub = (a, b) -> a - b;
//        Operation mul = (a, b) -> a * b;
        Operation div = (a, b) -> a / b;
        try {
            System.out.println(div.apply(6, 0));
        } catch (ArithmeticException e) {
            System.out.println("나눗셈 오류");
        }
//
//
//        System.out.println("익명 클래스 add: " + addAnon.apply(3, 4));
//        System.out.println("람다 add: "      + addLambda.apply(3, 4));
//        System.out.println(add.apply(3, 4));
//
//        System.out.println(sub.apply(9, 2));
//        System.out.println(mul.apply(3, 5));

//        Runnable hello = () -> System.out.println("(0개) 안녕하세요, 람다!");
//        hello.run();
//
//        Printer log = msg -> System.out.println("(1개) [로그] " + msg);
//        log.print("시작합니다");
//
//        Operation add = (a, b) -> a + b;
//        System.out.println("(2개) 10 + 20 = " + add.apply(10, 20));


//        ArrayList<String> names = new ArrayList<>(Arrays.asList("가나다", "가", "라마"));
//        System.out.println("정렬 전: " + names);
//
//        names.sort((s1, s2) -> s1.length() - s2.length());
//        names.sort((s1, s2) -> s2.length() - s1.length());
//        names.sort((s1, s2) -> s1.compareTo(s2));
//
//        System.out.println("정렬 후: " + names);

//        Predicate<String> isEmpty = s -> s.isEmpty();
//        System.out.println(isEmpty.test(""));
//
//        System.out.println(isEmpty.test("hello"));


        List<String> names = Arrays.asList("철수", "영희");
        names.forEach(System.out::println);
    }
}
