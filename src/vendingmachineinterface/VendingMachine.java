package vendingmachineinterface;

public class VendingMachine {
    private int totalMoney;
    private Drink[] drinks;

    public VendingMachine() {
        totalMoney = 0;
        drinks = new Drink[]{
                new Coke(),
                new Cider(),
                new Fanta(),
                new Water()
        };
    }

    public void insertMoney(int money) {
        if (money <= 0) {
            System.out.println("1월 이상 넣어주세요.");
            return;
        }

        totalMoney += money;
        System.out.println(money + "원을 넣었습니다.");
    }

    public void printMenu() {
        System.out.println();
        System.out.println("============== 자판기 ==============");

        for (int i = 0; i < drinks.length; i++) {
            Drink drink = drinks[i];
            System.out.print("[" + (i + 1) + "]" + drink.getName() + " : " + drink.getPrice() + "  ");

        }

        System.out.println();
        System.out.println("[5]돈 넣기  [6]종료");
        System.out.println("현재 금액 : " + totalMoney);
        System.out.println("====================================");

    }

    public void buy(int menuNumber) {
        if (menuNumber < 1 || menuNumber > drinks.length) {
            System.out.println("잘못된 음료 번호입니다.");
            return;
        }

        Drink drink = drinks[menuNumber - 1];

        if (totalMoney < drink.getPrice()) {
            System.out.println("금액이 부족합니다.");
            return;
        }

        totalMoney -= drink.getPrice();
        drink.dispense();
    }

    public int returnChange() {
        int change = totalMoney;
        totalMoney = 0;
        return change;
    }


}
