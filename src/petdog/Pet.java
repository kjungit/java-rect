package petdog;

public class Pet {
    private final String name;
    private final PetType type;
    private int fullness;
    private int happiness;
    private boolean sleeping;

    public Pet(String name) {
        this.name = name;
        this.type = PetType.DOG;
        this.fullness = 50;
        this.happiness = 50;
    }

    public Pet(String name, PetType type) {
        this.name = name;
        this.type = type;
        this.fullness = 50;
        this.happiness = 50;
    }

    public String getName() {
        return name;
    }


    public String getTypeValue() {
        return type.getLabel();
    }

    public int getFullness() {
        return fullness;
    }

    public int getHappiness() {
        return happiness;
    }

    public void showStatus() {
        if (80 <= happiness) {
            System.out.println("행복해요!");
        } else if (50 <= happiness) {
            System.out.println("그저 그래요!");
        } else {
            System.out.println("시무룩");
        }


        System.out.println("[" + getTypeValue() + "][" + name + "] 포만감: " + fullness + " / 행복: " + happiness);
    }

    public void feed() {
        if (cannotActBecauseSleeping()) {
            return;
        }

        fullness += 20;
        if (fullness > 100) fullness = 100;
        happiness += 5;
        if (happiness > 100) happiness = 100;
        System.out.println(name + "에게 먹이를 줬어요! 냠냠");
    }

    public void play() {
        if (cannotActBecauseSleeping()) {
            return;
        }

        happiness += 20;
        if (happiness > 100) happiness = 100;
        fullness -= 10;
        if (fullness < 0) fullness = 0;
        System.out.println(name + "와(과) 신나게 놀았어요!");
    }

    public void sleepPet() {
        if (isAlreadySleeping()) {
            return;
        }

        sleeping = true;
        System.out.println(name + "이(가) 잠들었어요... zzz");

        try {
            for (int i = 1; i <= 3; i++) {
                Thread.sleep(500);
                System.out.println(name + "은(는) 자는 중... " + i + "초");
            }
            happiness += 10;
            fullness += 10;
            if (happiness > 100) happiness = 100;
            if (fullness > 100) fullness = 100;

            System.out.println(name + "이(가) 잘 잤어요!");
            showStatus();
        } catch (InterruptedException e) {
            System.out.println(name + "이(가) 잠에서 깼어요.");
        } finally {
            sleeping = false;
        }
    }

    private boolean cannotActBecauseSleeping() {
        if (sleeping) {
            System.out.println(name + "은(는) 자는 중이라 지금은 아무것도 할 수 없어요.");
            return true;
        }
        return false;
    }

    private boolean isAlreadySleeping() {
        if (sleeping) {
            System.out.println(name + "은(는) 이미 자는 중이에요.");
            return true;
        }
        return false;
    }
}
