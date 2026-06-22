package com.management;

public class Member {
    private int id;
    private String name;
    private String email;
    private String phone;
    private Grade grade;
    private int pricePlanId;

    public Member () {};

    public Member(String name, String email, String phone, Grade grade, int pricePlanId) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.grade = grade;
        this.pricePlanId = pricePlanId;
    }

    public Member(String name, String email, String phone, Grade grade) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public void setPricePlanId(int pricePlanId) {
        this.pricePlanId = pricePlanId;
    }

    public int getPricePlanId() {
        return pricePlanId;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", grade=" + grade +
                '}';
    }

    public void printInfo() {
        System.out.println(
                "[" + grade + "] " +
                        name + " / " +
                        email + " / " +
                        phone + " / "
        );
    }
}