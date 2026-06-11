package vendingmachineinterface;

public class Coke implements Drink {
    private final String name = "콜라";
    private  final int price = 500;

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
        System.out.println("시원한 콜라가 나왔습니다!");
    }
}
