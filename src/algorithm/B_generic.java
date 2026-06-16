package algorithm;

// * 제네릭의 주요 특징
//  1. 타입 안전성(Type Safety): 컴파일 시점에서 잘못된 타입 사용을 방지하여 런타임 에러를 줄일 수 있다.
//  2. 재사용성: 동일한 코드가 다양한 데이터 타입에서 동작할 수 있도록 한다.
//  3. 캐스팅 제거: 불필요한 타입 캐스팅을 줄인다.

public class B_generic<T> {
    private T item;

    public void set(T item) {
        this.item = item;
    }

    public T get() {
        return item;
    }

    public static void main(String[] args) {
        B_generic<Integer> intBox = new B_generic<>();
        intBox.set(10);
        System.out.println(intBox.get());

        B_generic<String> stringBox = new B_generic<>();
        stringBox.set("abc");
        System.out.println(stringBox.get());
    }


}
