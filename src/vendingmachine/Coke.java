package vendingmachine;

public class Coke extends Drink {
    public Coke() {
        super("콜라", 500);
    }

    @Override
    public void dispense() {
        System.out.println("시원한 콜라가 나왔습니다!");
    }
}
