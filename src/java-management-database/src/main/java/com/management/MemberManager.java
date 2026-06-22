package com.management;

import java.util.List;

public class MemberManager {
    private final MemberDao memberDao;
    private final PricePlan pricePlan;

    public MemberManager(PricePlan pricePlan) {
        this.memberDao = new MemberDao();
        this.pricePlan = pricePlan;
    }

    public boolean add(Member member) {
        if (memberDao.findByEmail(member.getEmail(), pricePlan.getId()) != null) {
            return false;
        }
        member.setPricePlanId(pricePlan.getId());
        return memberDao.insert(member);
    }

    public Member findByEmail(String email, int pricePlanId) {
        return memberDao.findByEmail(email, pricePlanId);
    }

    public Member findByName(String name, int pricePlanId) {
        return memberDao.findByName(name, pricePlanId);
    }

    public List<Member> findAll(int pricePlanId) {
        return memberDao.findAll(pricePlanId);
    }

    public boolean update(String targetEmail, String newName, String newEmail, String newPhone, Grade grade, int pricePlanId) {
        Member existing = memberDao.findByEmail(targetEmail, pricePlanId);

        if (existing == null) {
            return false;
        }

        if (!targetEmail.equals(newEmail)) {
            Member duplicate = memberDao.findByEmail(newEmail, pricePlanId);
            if (duplicate != null) {
                return false;
            }
        }

        Member updated = new Member(newName, newEmail, newPhone, grade, existing.getPricePlanId());

        return memberDao.update(targetEmail, updated);
    }

    public boolean delete(String email) {
        return memberDao.delete(email);
    }

    public int getCount(int pricePlanId) {
        return memberDao.count(pricePlanId);
    }

    public boolean isFull(int pricePlanId) {
        return memberDao.count(pricePlanId) >= pricePlan.getCapacity();
    }

    public int getCapacity() {
        return pricePlan.getCapacity();
    }

    public void printAll(int pricePlanId) {
        List<Member> members = memberDao.findAll(pricePlanId);

        if (members.isEmpty()) {
            System.out.println("등록된 회원이 없습니다");
            return;
        }

        for (Member member : members) {
            System.out.println(member);
        }
    }
}