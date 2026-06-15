package tree;

public class MyTree {
    private static final class Node {
        private final int value;
        private Node left;
        private Node right;

        private Node (int value) {
            this.value = value;
        }

        private Node root;
    }

    private Node root;

    public void insert(int value) {
        root = insertNode(root, value);
    }

    private Node insertNode(Node node, int value) {
        if(node == null) {
            return new Node(value);
        }

        if(value < node.value) {
            node.left = insertNode(node.left, value);
        } else if (value > node.value) {
            node.right = insertNode(node.right, value);
        }

        return node;
    }

    public void preOrder() {
        System.out.print("전위: ");
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.value + " ");
        preOrder(node.left);
        preOrder(node.right);
    }



    public void inOrder() {
        System.out.print("중위: ");
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.value + " ");
        inOrder(node.right);
    }



    public void postOrder() {
        System.out.print("후위: ");
        postOrder(root);
        System.out.println();
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.value + " ");
    }

}
