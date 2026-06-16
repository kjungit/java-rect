package java_part_1;

public enum B_day {
    SUNDAY("일요일"),
    MONDAY("월요일"),
    TUESDAY("화요일");

    private String desc;

    B_day(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
