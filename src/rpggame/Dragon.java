package rpggame;

public class Dragon extends Monster{
    public Dragon(String name, int hp, int power){
        super(name, hp, power);
    }

    public Dragon(String name) {
        super(name, 120, 20);
    }
    @Override
    public void attack(Character target) {
        System.out.println(getName() + "의 화염 공격!");
        target.takeDamage(getPower());
    }
}
