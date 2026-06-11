package vendingmachineinterface;

public class Water implements Drink {
    private final String name = "물";
    private  final int price = 200;

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
        System.out.println("깔끔한 물이 나왔습니다!");
    }
}
