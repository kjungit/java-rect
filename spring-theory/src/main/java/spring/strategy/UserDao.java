package spring.strategy;

class UserDao {
    private Database db;

    UserDao(Database db) {
        this.db = db;
    }

    void context(StatementStrategy strategy) {
        db.open();                  // 공통: 자원 열기
        strategy.run(db);           // 변하는 부분을 전략에 위임
        db.close();                 // 공통: 자원 정리
    }

    <T> T context(ResultStatementStrategy<T> strategy) {
        db.open();

        try {
            return strategy.run(db);
        } finally {
            db.close();
        }
    }

    void deleteAll() {
        context(db -> {
            db.getUsers().clear();
            System.out.println("  [전략-익명] 전체 삭제");
        });
    }

    void add(User user) {
        context(db -> {
            db.getUsers().add(user);
            System.out.println("  [전략-익명] 추가: " + user.getName());
        });
    }

    User get(String id) {
        return context(db -> {
            for (User user : db.getUsers()) {
                if (user.getId().equals(id)) {
                    System.out.println("  [전략-익명] 조회: " + user.getName());
                    return user;
                }
            }

            return null;
        });
    }


}
