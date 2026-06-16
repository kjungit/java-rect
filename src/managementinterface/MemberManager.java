package managementinterface;

public class MemberManager {
    private Member[] members;
    private int memberCnt;

    public MemberManager(PricePlan pricePlan) {
        members = new Member[pricePlan.getCapacity()];
        memberCnt = 0;
    }

    public int getCount() {
        return memberCnt;
    }

    public int getCapacity() {
        return members.length;
    }

    public boolean isFull() {
        return memberCnt == members.length;
    }

    public boolean existsEmail(String email) {
        return findIndexByEmail(email) != -1;
    }

    public boolean add(Member member) {
        if (isFull()) {
            return false;
        }

        if (existsEmail(member.getEmail())) {
            return false;
        }

        members[memberCnt] = member;
        memberCnt++;

        return true;
    }

    public Member findByEmail(String email) {
        int index = findIndexByEmail(email);

        if (index == -1) {
            return null;
        }

        return members[index];
    }

    public Member findByName(String name) {
        for (int i = 0; i < memberCnt; i++) {
            if (members[i].getName().equals(name)) {
                return members[i];
            }
        }

        return null;
    }

    public void printAll() {
        if (memberCnt == 0) {
            System.out.println("등록된 회원이 없습니다.");
            return;
        }

        System.out.println("===== 전체 회원 목록 =====");

        for (int i = 0; i < memberCnt; i++) {
            members[i].printInfo();
        }
    }

    public boolean update(String targetEmail, String newName, String newEmail, String newPhone) {
        int targetIndex = findIndexByEmail(targetEmail);

        if (targetIndex == -1) {
            return false;
        }

        int duplicateIndex = findIndexByEmail(newEmail);

        if (duplicateIndex != -1 && duplicateIndex != targetIndex) {
            return false;
        }

        members[targetIndex].update(newName, newEmail, newPhone);
        return true;
    }

    public boolean delete(String email) {
        int targetIndex = findIndexByEmail(email);

        if (targetIndex == -1) {
            return false;
        }

        for (int i = targetIndex; i < memberCnt - 1; i++) {
            members[i] = members[i + 1];
        }

        members[memberCnt - 1] = null;
        memberCnt--;

        return true;
    }

    private int findIndexByEmail(String email) {
        for (int i = 0; i < memberCnt; i++) {
            if (members[i].getEmail().equals(email)) {
                return i;
            }
        }

        return -1;
    }
}