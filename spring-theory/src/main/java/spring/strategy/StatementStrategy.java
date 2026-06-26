package spring.strategy;

@FunctionalInterface
interface StatementStrategy {
    void run(Database db);          // 변하는 부분
}