package spring.singletone_2;

class UserDao {
    private static final UserDao instance = new UserDao();
    private UserDao() {}

    static UserDao getInstance() {
        return instance;
    }

    private final ConnectionMaker connectionMaker =
            SimpleConnectionMaker.getInstance();

    String findUser(String userId) {
        return userId + " 조회 [" + connectionMaker.makeConnection() + "]";
    }
}
