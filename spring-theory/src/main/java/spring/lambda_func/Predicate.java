package spring.lambda_func;

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
