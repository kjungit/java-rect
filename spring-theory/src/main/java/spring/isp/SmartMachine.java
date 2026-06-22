package spring.isp;

public class SmartMachine implements Printer, Scanner, Faxer{

    @Override
    public void print() {
        System.out.println("복합기임 인쇄도 함");
    }

    @Override
    public void scan() {
        System.out.println("복합기임 스캔도 함");
    }

    @Override
    public void fax() {
        System.out.println("복합기임 팩스도 함");
    }
}
