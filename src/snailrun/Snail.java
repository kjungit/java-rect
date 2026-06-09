package snailrun;

import java.util.Random;

public class Snail extends Thread{
    private static final int FINISH_LINE = 30;
    private static final int RANDOM_COUNT = 3;
    private final String name;
    private int position;
    private final Random random;
    private final Race race;
    private final int DELAY_SECOND = 300;

    Snail(String name, Race race) {
        this.name = name;
        this.race = race;

        this.position = 0;
        this.random = new Random();
    }

    public void run() {
        while (position < FINISH_LINE && !race.isOver()) {
            move();
            printProgress();

            try {
                Thread.sleep(DELAY_SECOND);
            } catch (InterruptedException e) {
                System.out.println(name + "스레드가 중단되었습니다.");
            }
        }

        if (isFinished()) {
            race.finish(name);
        }
    }


    private void move() {
        position += random.nextInt(RANDOM_COUNT) + 1;

        if (position > FINISH_LINE) {
            position = FINISH_LINE;
        }
    }

    private void printProgress() {
        StringBuilder bar = new StringBuilder();

        for (int i = 0; i < position; i++) {
            bar.append("=");
        }
        bar.append(">");

        System.out.println(name + " : " + bar);
    }

    boolean isFinished() {
        return position >= FINISH_LINE;
    }
}
