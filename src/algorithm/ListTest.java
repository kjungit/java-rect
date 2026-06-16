package algorithm;

import java.util.LinkedList;
import java.util.Objects;

class MyData {
    int value;

    public MyData(int value) {
        this.value = value;
    }


    static MyData create(int v) {
        return new MyData(v);
    }

    @Override
    public String toString() {
        return ""+value;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MyData myData = (MyData) o;
        return value == myData.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
public class ListTest {
    public static void main(String[] args) {
        LinkedList<MyData> list = new LinkedList<>();
        list.add(MyData.create(1));
        list.add(MyData.create(2));
        list.add(MyData.create(3));


        System.out.println(list.contains(MyData.create(2)));


    }

}