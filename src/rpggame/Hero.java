package rpggame;

public class Hero extends Character{
    public Hero(String name, int hp, int power) {
        super(name, hp, power);
    }

    public Hero(String name) {
        super(name, 100, 20);
    }
}
