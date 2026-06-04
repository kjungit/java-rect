package java_part_1;

import java.util.Scanner;

public class VendingMachineChallenge {
    static void main(String[] args) {
        int balance = 0;
        final int COKE = 500, CIDER = 700, FANTA = 300, WATER = 200;
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("================================= 자판기 ================================");
            System.out.println("[1]콜라-500원 [2]사이다-700원 [3]환타-300원 [4]물-200원 [5]돈넣기 [6]종료");
            System.out.println("현재 금액 : " + balance + "원");
            System.out.println("==========================================================================");

            int current = sc.nextInt();

            if (current == 1) {
                if (balance < COKE) {
                    System.out.println("잔액이 부족합니다. 입금을 먼저 해주세요.");
                } else {
                    balance = withdraw(balance, COKE);
                    System.out.println("남은 금액 : " + balance + "원");
                }
            } else if (current == 2) {
                if (balance < CIDER) {
                    System.out.println("잔액이 부족합니다. 입금을 먼저 해주세요.");
                } else {
                    balance = withdraw(balance, CIDER);
                    System.out.println("남은 금액 : " + balance + "원");
                }
            } else if (current == 3) {
                if (balance < FANTA) {
                    System.out.println("잔액이 부족합니다. 입금을 먼저 해주세요.");
                } else {
                    balance = withdraw(balance, FANTA);
                    System.out.println("남은 금액 : " + balance + "원");
                }
            } else if (current == 4) {
                if (balance < WATER) {
                    System.out.println("잔액이 부족합니다. 입금을 먼저 해주세요.");
                } else {
                    balance = withdraw(balance, WATER);
                    System.out.println("남은 금액 : " + balance + "원");
                }
            } else if (current == 5) {
                System.out.println("입금할 금액을 작성해주세요.");
                int amount_deposit = sc.nextInt();
                balance = deposit(balance, amount_deposit);

            } else if (current == 6) {
                System.out.println("자판기를 종료합니다.");
                break;
            } else {
                System.out.println("다시 눌러주세요.");
            }


        }

    }




    static int deposit(int balance, int amount) {
        return balance + amount;
    }

    static int withdraw(int balance, int amount) {
        return balance - amount;
    }
}
