package java
// * 다차원 배열

// * 2차월 배열
// 타입[][] 변수이름;

public class J_array_2 {

    public static void exam1() {
        int[][] scores = new int[3][2];
        // 0 : 0, 1
        // 1 : 0, 1
        scores[0][0] = 10;
        scores[0][1] = 20;
        scores[1][0] = 30;
        scores[1][1] = 40;
        scores[2][0] = 50;
        scores[2][1] = 60;

        System.out.println(scores.length);
        System.out.println(scores[0].length);

        // 중첩 반복문을 이용하여 모든 값을 출력하세요.
        for ( int i = 0; i < scores.length; i++ ) {
            for ( int j = 0; j < scores[i].length; j++ ) {
                System.out.println(scores[i][j]);
            }
        }

    }

    public static void exam2() {
        int[][] scores = {{1, 2}, {3, 4}};

        // 출력해주세요.

    }

    // * 가변 배열
    // 2차원 이상의 다차원 배열을 생성할 때 전체 배열 차수 중 마지막 차수의 길이를 지정하지 않고,
    // 추후에 각기 다른 길이의 배열을 생성함으로써 고정된 형태가 아닌 보다 유동적인 가변 배열을 구성할 수 있다.
    public static void exam3() {
        int[][] scores = new int[2][];

        scores[0] = new int[] {1, 2};
        scores[1] = new int[3];

        scores[1][0] = 3;
        scores[1][1] = 4;
        scores[1][2] = 5;

        // 출력해주세요.

    }

    // * 3차원 배열
    public static void exam4() {
        int[][][] scores = new int[2][2][2];
        int[][][] scores2 = new int[2][2][];
    }


    static void main(String[] args) {
        exam1();
    }
}
