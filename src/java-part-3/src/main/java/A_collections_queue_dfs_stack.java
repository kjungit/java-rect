// * DFS (깊이 우선 탐색, Depth-First Search)
// 그래프 또는 트리에서 시작 정점으로부터 한 방향으로 갈 수 있는 데까지 깊게 들어간 뒤,
// 더 갈 곳이 없으면 되돌아 나와 다른 경로를 탐색하는 알고리즘이다.
// Stack을 이용하여 구현하며, 방문한 정점을 다시 방문하지 않도록 visited 배열로 관리한다.
//
// BFS 와의 차이) BFS 는 Queue(먼저 들어온 것이 먼저 나옴)를 써서 가까운 정점부터 넓게 퍼지며 탐색하지만,
//              DFS 는 Stack(나중에 들어온 것이 먼저 나옴)을 써서 한 길로 깊게 파고들며 탐색한다.

import java.util.LinkedList;
import java.util.Stack;

// 1 ---- 2 ---- 6
// \     / \     /
//  \   /   4 - 5
//   \ /   / \
//    3 - 7 - 8 - 9

// 1, 2
// 1, 3
// 2, 3
// 2, 4
// 2, 6
// 3, 7
// 4, 5
// 4, 7
// 4, 8
// 5, 6
// 7, 8
// 8, 9

// 참고) 그래프(인접 리스트)를 표현하는 Graph 클래스는 A_collections_queue_bfs.java 에 이미 정의돼 있다
//      같은 패키지에 있으므로 여기서는 다시 만들지 않고 그대로 가져다 쓴다
//      (인접 리스트 개념 설명도 해당 파일 상단에 정리돼 있다)

public class A_collections_queue_dfs_stack {

    static void main(String[] args) {

        // 각 정점의 방문 여부를 기록하는 배열이다 (true 면 이미 방문한 정점이다)
        // 정점 번호를 1~9 로 쓰기 위해 크기를 9 + 1 로 잡는다
        boolean[] visited = new boolean[9 + 1];

        // 정점 9개짜리 그래프를 만든다
        Graph graph = new Graph(9);

        // 위 그림에 맞춰 간선을 하나씩 연결한다
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 6);
        graph.addEdge(3, 7);
        graph.addEdge(4, 5);
        graph.addEdge(4, 7);
        graph.addEdge(4, 8);
        graph.addEdge(5, 6);
        graph.addEdge(7, 8);
        graph.addEdge(8, 9);

        // DFS 시작 - 1번 정점부터 탐색을 시작한다
        int startVertex = 1;
        Stack<Integer> stack = new Stack<>(); // 앞으로 방문할 정점을 담아두는 스택이다 (BFS 의 Queue 자리에 Stack 이 들어간다)

        // 시작 정점을 스택에 넣는다
        stack.push(startVertex);

        System.out.println("정점 " + startVertex + "에서 시작하는 DFS");

        // 스택이 빌 때까지 반복한다 (더 이상 방문할 정점이 없으면 종료된다)
        while (!stack.isEmpty()) {
            int vertex = stack.pop(); // 스택의 맨 위에서 정점을 하나 꺼낸다 (나중에 들어온 것이 먼저 나온다)

            // 이미 방문한 정점이면 건너뛴다
            // (스택에는 같은 정점이 중복으로 들어갈 수 있어서, 꺼낼 때 한 번 더 확인한다)
            if (visited[vertex]) {
                continue;
            }

            visited[vertex] = true;          // 지금 꺼낸 정점을 방문 처리한다
            System.out.print(vertex + " ");  // 꺼낸 정점을 방문 순서대로 출력한다

            // 현재 정점과 연결된 이웃 정점들을 하나씩 확인한다
            for (int adj : graph.getAdjacencyList()[vertex]) {
                // 아직 방문하지 않은 이웃이라면 스택에 넣는다
                if (!visited[adj]) {
                    stack.push(adj); // 다음에 탐색하도록 스택에 추가한다
                }
            }
        }
        System.out.println();

    }
}
