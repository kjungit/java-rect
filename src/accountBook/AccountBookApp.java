package accountBook;

import java.util.Scanner;

public class AccountBookApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountBook book = new AccountBookImpl();

        while (true) {
            System.out.print("""
                    ===== 가계부 =====
                    1. 내역 추가
                    2. 내역 조회
                    3. 전체 삭제
                    4. 내역 삭제
                    5. 총 지출 합계
                    6. 종료
                    ================
                    번호 입력 : """);

            int menu = Integer.parseInt(sc.nextLine());

            switch (menu) {
                case 1:
                    book.addAccount();
                    break;
                case 2:
                    book.showAccount();
                    break;
                case 3:
                    book.deleteAll();
                    break;
                case 4:
                    book.deleteItem();
                    break;
                case 5:
                    book.allAccount();
                    break;
                case 6:
                    System.out.println("종료합니다");
                    return;
                default:
                    System.out.println("잘못된 번호입니다");
            }
        }
    }
}