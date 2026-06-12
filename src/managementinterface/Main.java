package managementinterface;

import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int plan = selectPlan();
        int capacity = plan * 10;

        MemberManager manager = new MemberManager(capacity);

        while (true) {
            printMenu(manager);
            int menu = inputNumber("> ");

            switch (menu) {
                case 1:
                    addMember(manager);
                    break;
                case 2:
                    findByEmail(manager);
                    break;
                case 3:
                    findByName(manager);
                    break;
                case 4:
                    manager.printAll();
                    break;
                case 5:
                    updateMember(manager);
                    break;
                case 6:
                    deleteMember(manager);
                    break;
                case 7:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("올바른 번호를 입력해주세요.");
            }
        }
    }

    private static int selectPlan() {
        while (true) {
            System.out.println("[요금제를 선택하세요]");
            System.out.println("[1]Lite : 10명 [2]Basic : 20명 [3]Premium : 30명");
            int plan = inputNumber("> ");
            if (plan >= 1 && plan <= 3) {
                return plan;
            }
            System.out.println("1~3번 중에서 선택해주세요.");
        }
    }

    private static void printMenu(MemberManager manager) {
        System.out.println();
        System.out.println("[수행할 업무 - 현재 회원수 : "
                + manager.getCount() + "/" + manager.getCapacity() + "]");
        System.out.println("[1]회원추가 [2]회원조회(메일) [3]회원조회(이름)");
        System.out.println("[4]전체조회 [5]수정 [6]삭제 [7]종료");
    }

    private static void addMember(MemberManager manager) {
        if (manager.isFull()) {
            System.out.println("회원이 꽉 찼습니다.");
            return;
        }

        int grade = selectGrade();
        String name = inputText("이름 > ");
        String email = inputText("이메일 > ");
        String phone = inputText("연락처 > ");
        Member member;

        if (grade == 1) {
            member = new NormalMember(name, email, phone);
        } else {
            member = new VipMember(name, email, phone);
        }

        boolean result = manager.add(member);

        if (result) {
            System.out.println("회원이 추가되었습니다.");
        } else {
            System.out.println("이미 존재하는 이메일이거나 정원이 가득 찼습니다.");
        }
    }

    private static int selectGrade() {
        while (true) {
            System.out.println("[회원 등급을 선택하세요]");
            System.out.println("[1]일반 [2]VIP");
            int grade = inputNumber("> ");

            if (grade == 1 || grade == 2) {
                return grade;
            }

            System.out.println("1 또는 2를 입력해주세요.");
        }
    }

    private static void findByEmail(MemberManager manager) {
        String email = inputText("조회할 이메일 > ");
        Member member = manager.findByEmail(email);

        if (member == null) {
            System.out.println("찾으시는 회원이 없습니다.");
            return;
        }

        member.printInfo();
    }

    private static void findByName(MemberManager manager) {
        String name = inputText("조회할 이름 > ");
        Member member = manager.findByName(name);

        if (member == null) {
            System.out.println("찾으시는 회원이 없습니다.");
            return;
        }

        member.printInfo();
    }

    private static void updateMember(MemberManager manager) {
        String targetEmail = inputText("수정할 회원의 이메일 > ");
        Member targetMember = manager.findByEmail(targetEmail);

        if (targetMember == null) {
            System.out.println("찾으시는 회원이 없습니다.");
            return;
        }

        String newName = inputText("새 이름 > ");
        String newEmail = inputText("새 이메일 > ");
        String newPhone = inputText("새 연락처 > ");

        boolean result = manager.update(targetEmail, newName, newEmail, newPhone);

        if (result) {
            System.out.println("수정이 완료되었습니다.");
        } else {
            System.out.println("수정에 실패했습니다. 이미 존재하는 이메일일 수 있습니다.");
        }

    }

    private static void deleteMember(MemberManager manager) {
        String email = inputText("삭제할 회원의 이메일 > ");
        boolean result = manager.delete(email);

        if (result) {
            System.out.println("삭제가 완료되었습니다.");
        } else {
            System.out.println("찾으시는 회원이 없습니다.");
        }

    }

    private static int inputNumber(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.");
            }
        }
    }

    private static String inputText(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("빈 값은 입력할 수 없습니다.");
        }

    }

}