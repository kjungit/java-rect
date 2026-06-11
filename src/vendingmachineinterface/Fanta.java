package vendingmachineinterface;

public class Fanta implements Drink {
    private final String name = "환타";
    private final int price = 300;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void dispense() {
        System.out.println("톡 쏘는 환타가 나왔습니다!");
    }
}
