package hashmap;

public class MyHashMap {
    static class Node {
        final String key;
        private Integer value;
        Node next;
        Node(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }


    private static final int DEFAULT_CAPACITY = 16;
    private final Node[] buckets;
    private int capacity;
    private int size;

    public MyHashMap() {
        this.capacity = DEFAULT_CAPACITY;
        buckets = new Node[capacity];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    private int getIndex(String key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    public void put(String key, Integer value) {
        final int index = getIndex(key);

        Node current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }

            current = current.next;
        }

        Node newNode = new Node(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
    }

    public Integer get(String key) {
        int index = getIndex(key);

        Node current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    public boolean containsKey(String key) {
        int index = getIndex(key);

        Node current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public Integer remove(String key) {
        int index = getIndex(key);

        Node current = buckets[index];
        Node prev = null;

        while(current != null) {
            if (current.key.equals(key)) {
                Integer removeValue = current.value;

                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }

                size--;
                return removeValue;
            }

            prev = current;
            current = current.next;
        }
        return null;
    }

    public void printBuckets() {
        for (int i = 0; i < buckets.length; i++) {
            System.out.print("bucket[" + i + "] : ");
            Node current = buckets[i];
            while (current != null) {
                System.out.print("[" + current.key + "=" + current.value + "] -> ");
                current = current.next;
            }
            System.out.println("null");
        }
    }
}
