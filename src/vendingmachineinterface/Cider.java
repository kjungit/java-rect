package vendingmachineinterface;

public class Cider implements Drink {
    private final String name = "사이다";
    private final int price = 500;

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
        System.out.println("톡 쏘는 사이다가 나왔습니다!");
    }
}
