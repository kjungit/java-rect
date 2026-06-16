package managementinterface;

public enum PricePlan {
    LITE(10),
    BASIC(20),
    PREMIUM(30);


    private final int capacity;

    PricePlan(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
