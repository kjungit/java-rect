package spring.solid.isp;

public class Main {
    public static void main(String[] args) {
        Printer simple = new SimplePrinter();
        simple.print();


        SmartMachine smart = new SmartMachine();
        smart.print();
        smart.scan();
        smart.fax();
    }
}
