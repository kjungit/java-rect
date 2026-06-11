package vendingmachine;

public class Fanta extends Drink {
    public Fanta() {
        super("환타", 300);
    }

    @Override
    public void dispense() {
        System.out.println("달콤한 환타가 나왔습니다!");
    }
}