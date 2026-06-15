package treemap;

public class Main {
    public static void main(String[] args) {
        MyTreeMap map = new MyTreeMap();

        System.out.println("=== 1. 데이터 추가 ===");
        map.put("banana", 2);
        map.put("apple", 1);
        map.put("cherry", 3);
        map.put("date", 4);
        map.put("blueberry", 5);

        map.printSorted();
        System.out.println("size = " + map.size());

        System.out.println("\n=== 2. 조회 ===");
        System.out.println("banana = " + map.get("banana"));
        System.out.println("apple = " + map.get("apple"));
        System.out.println("melon = " + map.get("melon"));

        System.out.println("\n=== 3. 같은 key 갱신 ===");
        map.put("banana", 20);

        System.out.println("banana = " + map.get("banana"));
        System.out.println("size = " + map.size());
        map.printSorted();

        System.out.println("\n=== 4. containsKey ===");
        System.out.println("contains apple = " + map.containsKey("apple"));
        System.out.println("contains melon = " + map.containsKey("melon"));

        System.out.println("\n=== 5. firstKey / lastKey ===");
        System.out.println("firstKey = " + map.firstKey());
        System.out.println("lastKey = " + map.lastKey());

        System.out.println("\n=== 6. 삭제 테스트 ===");
        System.out.println("remove apple = " + map.remove("apple"));
        map.printSorted();
        System.out.println("size = " + map.size());

        System.out.println("remove banana = " + map.remove("banana"));
        map.printSorted();
        System.out.println("size = " + map.size());

        System.out.println("remove melon = " + map.remove("melon"));
        map.printSorted();
        System.out.println("size = " + map.size());
    }
}