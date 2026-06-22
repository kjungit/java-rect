package spring.lsp;

public class Main {
    public static void main(String[] args) {
        Sparrow s = new Sparrow();
        Penguin p = new Penguin();

        s.eat();
        p.eat();

        s.fly();
        p.swim();
    }
}
