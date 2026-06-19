package algorithm;

import java.util.*;

class MyData implements Comparable<MyData>{
    int v;
    public MyData(int v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return String.valueOf(v);
    }

    @Override
    public int compareTo(MyData o) {
        return Integer.compare(v, o.v);
    }
}
public class Test {
    static void main(String[] args) {
        List<MyData> list = new LinkedList<>();

        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            list.add(new MyData(r.nextInt(50)));
        }

        list = quickSort(list);

        System.out.println(list);
    }

    static List<MyData> quickSort(List<MyData> list) {
        if (list.size() <= 1) return list;

        MyData pivot = list.remove(0);

        List<MyData> lesser = new LinkedList<>();
        List<MyData> greater = new LinkedList<>();

        for (MyData m : list) {

            if (pivot.compareTo(m) > 0) {
                lesser.add(m);
            } else {
                greater.add(m);
            }
        }

        List<MyData> merged = new LinkedList<>();
        merged.addAll(quickSort(greater));
        merged.add(pivot);
        merged.addAll(quickSort(lesser));



        return merged;
    }
}
