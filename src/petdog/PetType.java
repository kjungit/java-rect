package petdog;

public enum PetType {
    DOG("강아지"),
    CAT("고양이"),
    ELEPHANT("코끼리");

    private final String label;

    PetType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static PetType getByIndex(int index) {
        PetType[] types = PetType.values();

        if (index < 1 || index > types.length) {
            throw new IllegalArgumentException("잘못된 펫 타입 번호입니다.");
        }

        return types[index - 1];
    }
}
