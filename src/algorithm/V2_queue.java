package algorithm;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class V2_queue {
    public static void main(String[] args) {
        Deque<Integer> deque = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();

        deque.offerFirst(1);
        deque.offerLast(2);
        deque.offerFirst(3);
        deque.offerLast(4);
        deque.offerFirst(5);
        System.out.println(deque);

        System.out.println(deque.peekFirst());
        deque.pollFirst();
        System.out.println(deque);

        System.out.println(deque.peekLast());
        deque.pollLast();
        System.out.println(deque);

        System.out.println(deque.peekFirst());
        deque.pollFirst();
        System.out.println(deque);

        System.out.println(deque.peekLast());
        deque.pollLast();
        System.out.println(deque);

        System.out.println(deque.peekFirst());
        deque.pollFirst();
        System.out.println(deque);
    }
}
