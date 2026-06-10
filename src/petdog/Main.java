package petdog;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("반려동물의 종류를 선택해주세요, [1]강아지 [2]고양이 [3]코끼리 ");
        System.out.print("> ");

        PetType type;

        while (true){
            try {
                int typeNum = Integer.parseInt(sc.nextLine().trim());
                type = PetType.getByIndex(typeNum);
                break;
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력하세요.");
            } catch (IllegalArgumentException e) {
                System.out.println("1~3 중에 골라주세요.");
            }
        }

        System.out.print("반려동물의 이름을 지어주세요: ");
        String name = sc.nextLine();

        Pet pet = new Pet(name, type);

        pet.showStatus();


        while (true) {
            System.out.println("\n무엇을 할까요? [1]먹이주기 [2]놀아주기 [3]상태보기 [4]잠자기 [5]종료");
            System.out.print("> ");

            int menu;

            try {
                menu = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력하세요.");
                continue;
            }

            switch (menu) {
                case 1:
                    pet.feed();
                    pet.showStatus();
                    break;
                case 2:
                    pet.play();
                    pet.showStatus();
                    break;
                case 3:
                    pet.showStatus();
                    break;
                case 4:
                    pet.sleepPet();
                    break;
                case 5:
                    System.out.println("안녕 !");
                    return;
                default:
                    System.out.println("1~5 중에 골라주세요.");
            }
        }
    }
}
