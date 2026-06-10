package rpggame;

public class Character {
    private final String name;
    private int hp;
    private int power;


    public Character(String name, int hp, int power) {
        this.name = name;
        this.hp = hp;
        this.power = power;

    }

    public Character(String name) {
        this(name, 30, 5);
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getPower() {
        return power;
    }

    public void showStatus() {
        System.out.println(name + " (HP: " + hp + ")");
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void takeDamage(int dmg) {
        hp -= dmg;
        if (hp < 0) hp = 0;
    }

    public void attack(Character target) {
        System.out.println(name + "의 공격! " + target.getName() + "에게 " + power + " 피해");
        target.takeDamage(power);
    }
}
