package bingogame;

import java.util.*;

public class BingoGame {
    private static final int SIZE = 5;
    private static final int MAX = SIZE * SIZE;
    private static final int TARGET = 3;

    private final Scanner sc = new Scanner(System.in);
    private final Random random = new Random();

    private final int[][] playerBoard = new int[SIZE][SIZE];
    private final int[][] computerBoard = new int [SIZE][SIZE];

    private final boolean[][] playerMarked = new boolean [SIZE][SIZE];
    private final boolean[][] computerMarked = new boolean [SIZE][SIZE];

    // 이미 부른 숫자
    private final boolean[] called = new boolean[MAX + 1];

    public void play() {
        makeBoard(playerBoard);
        makeBoard(computerBoard);

        System.out.println("===== 빙고 게임 =====");
        System.out.println("컴퓨터와 번갈아 숫자를 불러 빙고를 완성하세요!");
        System.out.println("먼저 " + TARGET + "줄을 완성하면 승리합니다.");

        while (true) {
            System.out.println();
            System.out.println("===== 내 빙고판 =====");
            printBoard(playerBoard, playerMarked);

            int playerNum = playerPick();

            callNumber(playerNum, "내가");

            if(checkWin()) {
                break;
            }

            if (!hasAvailableNumber()) {
                printDraw();
                break;
            }

            int computerNum = computerPick();
            callNumber(computerNum, "컴퓨터가");

            if (checkWin()) {
                break;
            }

            printCurrentScore();
        }
    }

    private void makeBoard(int[][] board) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= MAX; i++) {
            nums.add(i);
        }

        Collections.shuffle(nums);

        int idx = 0;

        for(int r = 0; r < SIZE; r++) {
            for(int c = 0; c < SIZE; c++) {
                board[r][c] = nums.get(idx);
                idx++;
            }
        }

    }

    private void printBoard(int[][] board, boolean[][] marked) {
        for (int r = 0; r < SIZE; r++ ) {
            for (int c = 0; c < SIZE; c++) {
                if (marked[r][c]) {
                    System.out.print("[ *] ");
                } else {
                    System.out.printf("[%2d] ", board[r][c]);
                }
            }
            System.out.println();
        }
    }

    private void mark(int[][] board, boolean[][] marked, int num) {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c <SIZE; c++) {
                if (board[r][c] == num) {
                    marked[r][c] = true;
                    return;
                }
            }
        }
    }

    private void callNumber(int num, String who) {
        called[num] = true;

        System.out.println("> " + who + " 부른 숫자: " + num);

        mark(playerBoard, playerMarked, num);
        mark(computerBoard, computerMarked,  num);
    }

    private int playerPick() {
        while (true) {
            System.out.println("부를 숫자 입력 : ( 1 ~ " + MAX + " )");

            String input = sc.nextLine();

            int num;

            try {
                num = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력하세요.");
                continue;
            }

            if (num < 1 || num > MAX) {
                System.out.println("1 ~ " + MAX + " 사이로 입력하세요.");
                continue;
            }

            if (called[num]) {
                System.out.println("이미 부른 숫자입니다.");
                continue;
            }

            return num;
        }
    }

    private int computerPick() {
        int num;
        do {
            num = random.nextInt(MAX) + 1;
        } while (called[num]);

        return num;
    }

    private int countBingo(boolean[][] marked) {
        int count = 0;

        for (int r = 0; r < SIZE; r++) {
            boolean allMarked = true;

            for (int c = 0; c < SIZE; c++) {
                if (!marked[r][c]) {
                    allMarked = false;
                    break;
                }
            }

            if (allMarked) {
                count++;
            }
        }

        for (int c = 0; c < SIZE; c++) {
            boolean allMarked = true;

            for (int r = 0; r < SIZE; r++) {
                if (!marked[r][c]) {
                    allMarked = false;
                    break;
                }
            }

            if (allMarked) {
                count++;
            }
        }

        boolean diagonal1 = true;

        for (int i = 0; i < SIZE; i++) {
            if (!marked[i][i]) {
                diagonal1 = false;
                break;
            }
        }

        if (diagonal1) count++;

        boolean diagonal2 = true;

        for (int i = 0; i < SIZE; i++) {
            if (!marked[i][SIZE - 1 - i]) {
                diagonal2 = false;
                break;
            }
        }

        if (diagonal2) count++;

        return count;
    }

    private boolean checkWin() {
        int playerBingo = countBingo(playerMarked);
        int computerBingo = countBingo(computerMarked);

        if (playerBingo >= TARGET && computerBingo >= TARGET) {
            System.out.println("무승부");
            printResult(playerBingo, computerBingo);
            return true;
        }

        if (playerBingo >= TARGET) {
            System.out.println("내가 승리했습니다!");
            printResult(playerBingo, computerBingo);
            return true;
        }

        if (computerBingo >= TARGET) {
            System.out.println("컴퓨터가 승리했습니다!");
            printResult(playerBingo, computerBingo);
            return true;
        }

        return false;
    }

    private void printResult(int playerBingo, int computerBingo) {
        System.out.println();
        System.out.println("최종 빙고 줄 > 나: " + playerBingo + "줄, 컴퓨터: " + computerBingo + "줄");

        System.out.println();
        System.out.println("===== 내 최종 빙고판 =====");
        printBoard(playerBoard, playerMarked);

        System.out.println();
        System.out.println("===== 컴퓨터 최종 빙고판 =====");
        printBoard(computerBoard, computerMarked);
    }

    private void printCurrentScore() {
        int playerBingo = countBingo(playerMarked);
        int computerBingo = countBingo(computerMarked);

        System.out.println();
        System.out.println("현재 빙고 줄 > 나 : " + playerBingo + "줄, 컴퓨터 : " + computerBingo + "줄");
    }

    private boolean hasAvailableNumber() {
        for (int i = 1; i <= MAX; i++) {
            if (!called[i]) {
                return true;
            }
        }
        return false; // 모든 숫자를 이미 불렀다는 뜻.
    }

    private void printDraw() {
        int playerBingo = countBingo(playerMarked);
        int computerBingo = countBingo(computerMarked);

        System.out.println("더이상 부를 숫자가 없습니다. 무승부");

        printResult(playerBingo, computerBingo);
    }
}
