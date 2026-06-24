package spring.singletone_1;

public class NaiveTicketMachine {
    int lastNumber = 0;

    public int getNum() {
        return lastNumber;
    }

    public void issue() {
        this.lastNumber++;
    }
}
