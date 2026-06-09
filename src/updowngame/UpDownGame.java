package updowngame;

import java.util.Random;
import java.util.Scanner;

public class UpDownGame {
    private final static int SCOPE = 100;
    private  static  final int MAX_COUNT = 7;

    Random random = new Random();
    int answer;
    int count = 0;

    Scanner sc = new Scanner(System.in);

    public void play() {
        while (true) {
            resetGame();
            firstGame();

            if (!reStart()) {
                System.out.println("게임 끝");
                break;
            }
        }

    }

    private void resetGame() {
        answer = random.nextInt(SCOPE) + 1;
        count = 0;
    }

    private void firstGame() {
        while (true) {

            if (count >= MAX_COUNT) {
                System.out.println("기회 제한 7번이 초과 되었습니다. 게임 끝");
                break;
            }


            System.out.println("숫자를 맞혀보세요! (1 ~ 100)\n");
            System.out.print("입력 : ");

            String inputText = sc.nextLine();
            int input;

            try {
                input = Integer.parseInt(inputText.trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력하세요.");
                continue;
            }


            count++;

            if (input < 1 || input > SCOPE) {
                System.out.println("1 ~ 100 사이로 입력해 주세요.");
                continue;
            }



            if (answer > input) {
                System.out.println("UP! 더 큰 수 입니다.");
            } else if (answer < input) {
                System.out.println("UP! 더 작은 수 입니다.");
            } else {
                System.out.println("정답입니다! " + count + "번 만에 맞혔어요.");
                break;
            }
        }
    }

    private boolean reStart() {
        while (true) {
            System.out.println("한 판 더? ( y / n )");

            String input = sc.nextLine();

            if (input.equals("y")) {
                count = 0;
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                System.out.println("y / n 만 입력 해주세요.");
            }
        }
    }



}


