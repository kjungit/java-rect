package accountBook;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class AccountBookImpl implements AccountBook {
    private Map<String, List<Item>> data = new TreeMap<>(Comparator.reverseOrder());
    private Scanner sc = new Scanner(System.in);

    public void addAccount() {
        String date = inputDate();

        List<Item> items;

        if(data.containsKey(date)) {
            items = data.get(date);
        } else {
            items = new ArrayList<>();
        }

        boolean isAdding = true;

        while (isAdding) {
            System.out.println("항목 이름 입력 : ");
            String name = sc.nextLine();

            int price = inputPrice();

            Item item = new Item(name, price);
            items.add(item);

            while (true) {
                System.out.println("항목을 더 추가할까요? (y / n)");
                String answer = sc.nextLine();

                if (answer.equalsIgnoreCase("y")) {
                    break;
                }

                if (answer.equalsIgnoreCase("n")) {
                    isAdding = false;
                    break;
                }
            }

        }
        data.put(date, items);
        printAccount();

    }

    private  int inputPrice() {
        while (true) {
            System.out.println("항목 금액 입력 : ");
            String priceText = sc.nextLine();

            try {
                return Integer.parseInt(priceText);
            } catch (NumberFormatException e) {
                System.out.println("금액은 숫자로만 입력해주세요.");
            }
        }
    }

    private String inputDate() {
        while (true) {
            System.out.println("날짜를 입력해주세요. 예시: YYYY-MM-DD : ");
            String date = sc.nextLine();
            if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("날짜 형식이 올바르지 않습니다. YYYY-MM-DD 형식으로 입력해주세요.");
                continue;
            }
            try {
                LocalDate.parse(date);
                return date;
            } catch (DateTimeParseException e) {
                System.out.println("존재하지 않는 날짜입니다. 다시 입력해주세요.");
            }
        }

    }

    private void printAccount() {
        int grandTotal = 0;

        System.out.println();
        System.out.println("===== 가계부 =====");

        for (String date : data.keySet()) {
            List<Item> items = data.get(date);

            int dateTotal = 0;
            System.out.println("[" + date + "]");
            for (Item item : items) {
                System.out.println(item.getName() + " : " + item.getPrice() + "원");
                dateTotal += item.getPrice();
            }

            System.out.println("합계 : " + dateTotal + "원");
            System.out.println();

            grandTotal += dateTotal;
        }

        System.out.println("전체 합계 : " + grandTotal);
    }

    public void showAccount() {
        if (data.isEmpty()) {
            System.out.println("조회할 내역이 없습니다.");
            return;
        }

        System.out.println(data.keySet());

        while (true) {
            System.out.println("확인할 날짜를 선택해주세요. 예시: YYYY-MM-DD / 돌아가기 'n' ");
            String date = sc.nextLine();


            if(date.matches("n")) {
                break;
            }

            if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("날짜 형식이 올바르지 않습니다. YYYY-MM-DD 형식으로 입력해주세요.");
                continue;
            }

            if (!data.containsKey((date))) {
                System.out.println("작성한 날짜의 기록이 없습니다.");
            } else {
                List<Item> items = data.get(date);
                int total = 0;

                System.out.println("\n===== " + date + " 내역 =====");

                for (Item item : items) {
                    System.out.println(item.getName() + " : " + item.getPrice() + "원");
                    total += item.getPrice();
                }

                System.out.println("합계 : " + total + "원");
                break;
            }
        }
    }

    public void deleteAll() {
        if (data.isEmpty()) {
            System.out.println("삭제할 내역이 없습니다.");
            return;
        }

        System.out.println(data.keySet());

        while (true) {
            System.out.println("삭제할 날짜를 선택해주세요. 예시: YYYY-MM-DD / 돌아가기 'n' ");
            String date = sc.nextLine();


            if(date.matches("n")) {
                break;
            }

            if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("날짜 형식이 올바르지 않습니다. YYYY-MM-DD 형식으로 입력해주세요.");
                continue;
            }

            if (!data.containsKey((date))) {
                System.out.println("작성한 날짜의 기록이 없습니다.");
            } else {
                data.remove(date);
                System.out.println("선택한 날짜의 기록을 삭제하였습니다.");
                break;
            }
        }
    }

    public void deleteItem() {


        System.out.println(data.keySet());

        while (true) {
            if (data.isEmpty()) {
                System.out.println("삭제할 내역이 없습니다.");
                return;
            }


            System.out.println("삭제할 날짜를 선택해주세요. 예시: YYYY-MM-DD / 돌아가기 'n' ");
            String date = sc.nextLine();

            if(date.matches("n")) {
                break;
            }

            if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("날짜 형식이 올바르지 않습니다. YYYY-MM-DD 형식으로 입력해주세요.");
                continue;
            }

            if (!data.containsKey((date))) {
                System.out.println("작성한 날짜의 기록이 없습니다.");
            } else {
                List<Item> items = data.get(date);

                while (true) {

                    System.out.println("[" + date + "]");

                    for (int i = 0; i < items.size(); i++) {
                        Item item = items.get(i);
                        System.out.println((i + 1) + ". " + item.getName() + " : " + item.getPrice() + "원");
                    }

                    if(items.size() == 0) {
                        System.out.println("삭제할 항목이 없습니다.");
                        break;
                    }


                    System.out.println("삭제할 항목의 번호를 입력해주세요. / 돌아가기 'n");

                    String input = sc.nextLine();
                    if(input.equalsIgnoreCase("n")) {
                        break;
                    }

                    int itemNumber;

                    try {
                        itemNumber = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        System.out.println("항목 번호는 번호로만 입력해주세요.");
                        continue;
                    }

                    if (itemNumber < 1 || itemNumber > items.size()) {
                        System.out.println("존재하지 않는 항목입니다.");
                        continue;
                    }

                    Item removeItem = items.remove(itemNumber - 1);
                    System.out.println(removeItem.getName() + " 항목을 삭제했습니다.");

                    if (items.isEmpty()) {
                        data.remove(date);
                    }

                }

            }
        }
    }

    public void allAccount() {
        if (data.isEmpty()) {
            System.out.println("조회할 내역이 없습니다.");
        }

        int total = 0;
        for (List<Item> items : data.values()) {
            for (Item item: items) {
                total += item.getPrice();
            }
        }

        System.out.println("총 지출 합계 : " + total);
    }
}