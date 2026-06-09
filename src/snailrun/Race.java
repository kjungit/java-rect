package snailrun;

public class Race {
    private  volatile boolean over = false;

    public boolean isOver(){
        return over;
    }

    public synchronized void finish(String name) {
        if (!over) {
            over = true;
            System.out.println();
            System.out.println("*** 우승: " + name + "***");
        }
    }
}
