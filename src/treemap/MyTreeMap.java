package treemap;

public class MyTreeMap {

    private static final class Node {
        private String key;
        private Integer value;
        private Node left;
        private Node right;

        private Node(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;
    private int size;

    public void put(String key, Integer value) {
        validateKey(key);
        root = putNode(root, key, value);
    }

    private Node putNode(Node node, String key, Integer value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            node.left = putNode(node.left, key, value);
        } else if (cmp > 0) {
            node.right = putNode(node.right, key, value);
        } else {
            node.value = value;
        }

        return node;
    }

    public Integer get(String key) {
        validateKey(key);
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);

            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current.value;
            }
        }

        return null;
    }

    public Integer remove(String key) {
        validateKey(key);
        Integer oldValue = get(key);

        if (oldValue == null && !containsKey(key)) {
            return null;
        }

        root = removeNode(root, key);
        size--;

        return oldValue;
    }

    private Node removeNode(Node node, String key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            node.left = removeNode(node.left, key);
        } else if (cmp > 0) {
            node.right = removeNode(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = findMin(node.right);

            node.key = successor.key;
            node.value = successor.value;

            node.right = removeNode(node.right, successor.key);
        }

        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    public boolean containsKey(String key) {
        validateKey(key);

        Node current = root;

        while (current != null) {
            int cmp = key.compareTo(current.key);

            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return true;
            }
        }

        return false;
    }

    public String firstKey() {
        if (root == null) {
            return null;
        }

        Node current = root;

        while (current.left != null) {
            current = current.left;
        }

        return current.key;
    }

    public String lastKey() {
        if (root == null) {
            return null;
        }

        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.key;
    }

    public int size() {
        return size;
    }

    public void printSorted() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.print("[" + node.key + "=" + node.value + "] ");
        inOrder(node.right);
    }

    private void validateKey(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key는 null일 수 없습니다.");
        }
    }
}