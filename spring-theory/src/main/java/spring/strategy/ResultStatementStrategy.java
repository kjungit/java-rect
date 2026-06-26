package spring.strategy;

@FunctionalInterface
interface ResultStatementStrategy<T> {
    T run(Database db);
}