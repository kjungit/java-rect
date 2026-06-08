package memberManagement;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static int totalCnt = 0;

    public static void main(String [] args) {
        int planNum = printPricePlan();

        totalCnt = planNum * 10;
        ArrayList<Member> members = new ArrayList<>();

         
        while(true) {
            int choice = printMenu(members.size());

            switch (choice) {
                case 1:
                    // 회원 추가
                    addMember(members);
                    break;
                case 2:
                    // 이메일 조회
                    selectEmail(members);
                    break;
                case 3:
                    // 이름 조회
                    selectName(members);
                    break;
                case 4:
                    // 전체 조회
                    selectAll(members);
                    break;
                case 5:
                    // 회원 수정
                    updateMember(members);
                    break;
                case 6:
                    // 회원 삭제
                    deleteMember(members);
                    break;
                case 7:
                    System.out.println("이용해주셔서 감사합니다.");
                    return;
                default:
                    System.out.println("올바른 번호를 입력하세요.");
            }
        }
    }

    public static int printPricePlan() {
        while(true) {
            System.out.println("[요금제를 선택하세요]");
            System.out.println("[1]Lite : 10명 [2]Basic : 20명 [3]Premium : 30명");
            System.out.print("> ");

            int num = inputNumber();

            if (num >= 1 && num <= 3) {
                return num;
            }

            System.out.println("올바른 요금제를 선택해주세요.");
        }
    }

    public static int printMenu(int memberCnt) {
        System.out.println();
        System.out.println("[수행할 업무를 선택하세요 - 현재 회원수 : " + memberCnt + "/" + totalCnt + "]");
        System.out.println("[1]회원추가 [2]회원조회(메일) [3]회원조회(이름)");
        System.out.println("[4]회원전체조회 [5]회원정보 수정 [6]회원삭제");
        System.out.println("[7]프로그램 종료");
        System.out.print("> ");

        return inputNumber();
    }

    public static void addMember(ArrayList<Member> members) {
        if (members.size() >= totalCnt) {
            System.out.println("회원이 꽉 찼습니다.");
            return;
        }

        System.out.print("이름을 입력하세요 : ");
        String name = sc.nextLine();

        String email = inputEmail("이메일을 입력하세요 : ");

        if(checkEmail(members, email)) {
            System.out.println("이미 존재하는 회원입니다.");
            return;
        }

        String phone = inputPhone("연락처를 입력하세요 : ");

        members.add(new Member(name, email, phone));

        System.out.println("회원이 추가되었습니다.");
    }

    public static boolean checkEmail(ArrayList<Member> members, String email) {
        for (int i = 0; i < members.size(); i++) {
            if (email.equals(members.get(i).getEmail())) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkEmailExceptIndex (ArrayList<Member> members, String email, int exceptIndex) {
        for (int i = 0; i < members.size(); i++) {
            if (i != exceptIndex && email.equals(members.get(i).getEmail())) {
                return true;
            }
        }
        return false;
    }

    public static void selectEmail(ArrayList<Member> members) {
        System.out.print("조회할 이메일을 입력하세요: ");
        String email = sc.nextLine();

        int idx = findIndexByEmail(members, email);

        if(idx == -1) {
            System.out.println("찾으시는 정보가 없습니다.");
            return;
        }

        printMember(members, idx);
    }

    public static void selectName(ArrayList<Member> members) {
        System.out.print("조회할 이름을 입력하세요: ");
        String name = sc.nextLine();


        boolean found = false;

        for (int i = 0; i < members.size(); i++) {
            if (name.equals(members.get(i).getName())) {
                printMember(members, i);
                found = true;
            }
        }

        if (!found) {
            System.out.println("찾으시는 정보가 없습니다.");
        }
    }

    public static void selectAll(ArrayList<Member> members) {
        if (members.isEmpty()) {
            System.out.println("등록된 회원이 없습니다.");
            return;
        }

        System.out.println("[전체 회원 목록]");
        for (int i = 0; i < members.size(); i++) {
            System.out.print((i + 1) + ". ");
            printMember(members, i);
        }
    }

    public static void updateMember(ArrayList<Member> members) {
        System.out.print("수정할 회원의 이메일을 입력하세요: ");
        String email = sc.nextLine();

        int idx = findIndexByEmail(members, email);

        if (idx == -1) {
            System.out.println("찾으시는 회원이 없습니다.");
            return;
        }

        System.out.println("수정할 이름을 입력하세요.");
        String newName = sc.nextLine();

        String newEmail = inputEmail(("수정할 이메일을 입력하세요 : "));

        if (checkEmailExceptIndex(members, newEmail, idx)) {
            System.out.println("이미 존재하는 이메일 입니다. 수정할 수 없습니다.");
            return;
        }

        String newPhone = inputPhone("수정할 연락처를 입력하세요 : ");

        members.get(idx).setName(newName);
        members.get(idx).setEmail(newEmail);
        members.get(idx).setPhone(newPhone);


        System.out.println("수정이 완료되었습니다.");
    }

    public static void deleteMember(ArrayList<Member> members) {
        System.out.print("삭제할 회원의 이메일을 입력하세요: ");
        String email = sc.nextLine();

        int idx = findIndexByEmail(members, email);

        if (idx == -1) {
            System.out.println("찾으시는 회원이 없습니다.");
            return;
        }

        members.remove(idx);

        System.out.println("삭제가 완료되었습니다.");
    }

    public static int findIndexByEmail(ArrayList<Member> members, String email) {
        for (int i = 0; i < members.size(); i++) {
            if (email.equals(members.get(i).getEmail())) {
                return i;
            }
        }
        return -1;
    }

    public static void printMember(ArrayList<Member> members, int index) {
        System.out.println(
                "[이름] " + members.get(index).getName()
                        + ", [이메일] " + members.get(index).getEmail()
                        + ", [연락처] " + members.get(index).getPhone()
        );
    }


    public static int inputNumber() {
        while(true) {
            String input = sc.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    public static boolean isValidPhone(String phone) {
        return phone.matches("^\\d{10,11}$");
    }

    public static String inputEmail(String message) {
        while (true) {
            System.out.print(message);
            String email = sc.nextLine();
            if (isValidEmail(email)) {
                return email;
            }
            System.out.println("이메일 형식이 올바르지 않습니다. 예: test@gmail.com");
        }
    }

    public static String inputPhone(String message) {
       while (true) {
           System.out.println(message);
           String phone = sc.nextLine();

           if (isValidPhone(phone)){
               return phone;
           }

           System.out.print("연락처는 숫자만 10~11자리로 입력해주세요. 예: 01012345678");
       }
    }
}
