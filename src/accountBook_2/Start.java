package accountBook_2;

import java.util.Scanner;

public class Start {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AccountBook book = new AccountBookFileImpl();

        while(true){

            System.out.println("""
                    ===== 가계부(File) =====
                    1. 내역 추가
                    2. 내역 조회
                    3. 삭제
                    4. 종료
                    """);

            int menu = Integer.parseInt(sc.nextLine());

            switch(menu){

                case 1:
                    book.addAccount();
                    break;

                case 2:
                    book.showAccount();
                    break;

                case 3:
                    book.deleteAccount();
                    break;

                case 4:
                    System.out.println("종료");
                    return;

                default:
                    System.out.println("잘못된 번호");
            }
        }
    }
}