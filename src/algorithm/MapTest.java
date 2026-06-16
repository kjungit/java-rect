package algorithm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


class MyData2 {
    int v;

    public MyData2(int v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "["+v+"]";
    }
}


public class MapTest {
    public static void main(String[] args) {
        Map<MyData2, Integer> map = new ConcurrentHashMap<>();

        map.put(new MyData2(1), 1);
        map.put(new MyData2(2), 2);
        map.replace(new MyData2(1), 1, 11);



        System.out.println(map. remove(new MyData2 ( 2), 3)) ;
        System.out.println(map);
        System.out.println(map.get(new MyData2( 1)));
        System.out.println(map.getOrDefault(new MyData2( 3), -1));
        System.out.println(map.values());
        System.out.println(map.keySet().toArray().length);
    }

}


// ARRAY 장점 + LIST 장점 => 유연하면서도 빠르게 값을 찾아낼 수 있음
// MAP : key -> hash function -> hash -> bucket index -> List -> Data