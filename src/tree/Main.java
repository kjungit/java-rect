package tree;

public class Main {
    public static void main(String[] args) {
        MyTree tree = new MyTree();

        int[] values = {50, 30, 70, 20, 40, 60, 80};

        for (int value : values) {
            tree.insert(value);
        }

        tree.preOrder();
        tree.inOrder();
        tree.postOrder();
    }
}