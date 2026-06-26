package spring.strategy;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        UserDao dao = new UserDao(db);

        dao.deleteAll();
        dao.add(new User("u1", "김"));
        dao.add(new User("u2", "이"));

        System.out.println("\n현재 사용자 수: " + db.getUsers().size());
        for (User u : db.getUsers()) System.out.println("사용자: " + u.getName());


        User user = dao.get("u1");

        System.out.println(user.getName());
    }

}
