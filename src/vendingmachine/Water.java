package vendingmachine;

public class Water extends Drink {
    public Water() {
        super("물", 200);
    }

    @Override
    public void dispense() {
        System.out.println("깔끔한 물이 나왔습니다!");
    }
}