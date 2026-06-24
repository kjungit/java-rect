package spring.singletone_2;

public class SimpleConnectionMaker implements ConnectionMaker{
    private static final SimpleConnectionMaker instance = new SimpleConnectionMaker();
    private SimpleConnectionMaker() {}

    static SimpleConnectionMaker getInstance() {
        return instance;
    }

    public String makeConnection() {
        return "DB연결";
    }
}
