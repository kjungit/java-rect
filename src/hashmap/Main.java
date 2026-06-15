package hashmap;

public class Main {
    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();

        System.out.println("=== 1. 처음 상태 ===");
        System.out.println("size = " + map.size());
        map.printBuckets();

        System.out.println("\n=== 2. 데이터 추가 ===");
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);

        System.out.println("size = " + map.size());
        map.printBuckets();

        System.out.println("\n=== 3. 데이터 조회 ===");
        System.out.println("apple = " + map.get("apple"));
        System.out.println("banana = " + map.get("banana"));
        System.out.println("cherry = " + map.get("cherry"));
        System.out.println("melon = " + map.get("melon"));

        System.out.println("\n=== 4. 같은 key로 put하면 value 갱신 ===");
        map.put("apple", 10);

        System.out.println("apple = " + map.get("apple"));
        System.out.println("size = " + map.size());
        map.printBuckets();

        System.out.println("\n=== 5. containsKey 확인 ===");
        System.out.println("contains apple = " + map.containsKey("apple"));
        System.out.println("contains melon = " + map.containsKey("melon"));

        System.out.println("\n=== 6. 삭제 ===");
        Integer removedValue = map.remove("banana");

        System.out.println("removed banana value = " + removedValue);
        System.out.println("banana = " + map.get("banana"));
        System.out.println("size = " + map.size());
        map.printBuckets();

        System.out.println("\n=== 7. 충돌 테스트 ===");
        MyHashMap collisionMap = new MyHashMap();

        collisionMap.put("FB", 100);
        collisionMap.put("Ea", 200);

        System.out.println("FB = " + collisionMap.get("FB"));
        System.out.println("Ea = " + collisionMap.get("Ea"));
        System.out.println("size = " + collisionMap.size());
        collisionMap.printBuckets();
    }
}