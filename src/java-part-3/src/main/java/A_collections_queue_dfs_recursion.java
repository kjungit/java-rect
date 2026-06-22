// * DFS (깊이 우선 탐색, Depth-First Search) - 재귀(Recursion) 방식
// Stack 자료구조를 직접 만들지 않고, "메서드가 자기 자신을 호출"하는 방식으로 깊게 파고든다.
// 사실 재귀 호출도 내부적으로는 호출 스택(call stack)을 쓰기 때문에 스택 방식과 원리가 같다.
// (스택을 직접 다루는 방식은 A_collections_queue_dfs.java 에 정리돼 있다)

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

public class A_collections_queue_dfs_recursion {

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

        // DFS 시작 - 1번 정점부터 재귀 탐색을 시작한다
        int startVertex = 1;

        System.out.println("정점 " + startVertex + "에서 시작하는 DFS (재귀)");
        dfsRecursive(graph, startVertex, visited);
        System.out.println();

    }

    // 재귀 방식 DFS - 현재 정점(vertex)을 방문하고, 이웃들을 향해 다시 자기 자신을 호출하며 깊게 들어간다
    static void dfsRecursive(Graph graph, int vertex, boolean[] visited) {
        visited[vertex] = true;          // 지금 들어온 정점을 방문 처리한다
        System.out.print(vertex + " ");  // 방문 순서대로 출력한다

        // 현재 정점과 연결된 이웃 정점들을 하나씩 확인한다
        for (int adj : graph.getAdjacencyList()[vertex]) {
            // 아직 방문하지 않은 이웃이라면 그 이웃으로 더 깊이 파고든다 (자기 자신을 다시 호출한다)
            if (!visited[adj]) {
                dfsRecursive(graph, adj, visited);
            }
        }
        // for 문이 끝나면 더 갈 곳이 없는 것이므로, 자연스럽게 이전 정점(호출한 쪽)으로 되돌아간다 (백트래킹)
    }
}
