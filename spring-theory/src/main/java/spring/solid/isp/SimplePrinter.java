package spring.solid.isp;

public class SimplePrinter implements Printer{

    @Override
    public void print() {
        System.out.println("인쇄만 합니다");
    }
}
