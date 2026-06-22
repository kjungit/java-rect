package com.management;

public enum PricePlan {
    LITE(1, "Lite", 10),
    BASIC(2, "Basic", 20),
    PREMIUM(3, "Premium", 30);

    private final int id;
    private final String name;
    private final int capacity;

    PricePlan(int id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getId() {
        return id;
    }

    public static PricePlan from(String name) {
        for (PricePlan plan : values()) {
            if (plan.name.equalsIgnoreCase(name)) {
                return plan;
            }
        }

        throw new IllegalArgumentException(
                "존재하지 않는 요금제입니다."
        );
    }
}