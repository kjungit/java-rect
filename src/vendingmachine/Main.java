package vendingmachine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VendingMachine machine = new VendingMachine();

        while (true) {
            machine.printMenu();

            int choice = inputNumber(sc, "원하는 메뉴를 선택하세요 >");

            if (choice >= 1 && choice <= 4) {
                machine.buy(choice);
            } else if (choice == 5) {
                int money = inputNumber(sc, "넣을 금액 > ");
                machine.insertMoney(money);
            } else if (choice == 6) {
                int change = machine.returnChange();
                System.out.println();
                System.out.println("잔돈 " + change + "원이 반환되었습니다.");
                System.out.println("프로그램을 종료합니다.");
                break;
            } else {
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
            }
        }

        sc.close();
    }

    private static int inputNumber(Scanner sc, String message) {
        while (true) {
            System.out.println(message);
            String input = sc.nextLine();

            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.");
            }
        }
    }
}