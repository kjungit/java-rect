package java_part_1;

public class B_enum {
    public static void main(String[] args) {
        exam2();
    }
    public static void exam2() {
        for (B_day day : B_day.values()) {
            System.out.println(day.getDesc());
        }
    }
}
