// * BFS (너비 우선 탐색, Breadth-First Search)
// 그래프 또는 트리에서 시작 정점으로부터 가까운 정점부터 차례대로 탐색하는 알고리즘이다.
// Queue를 이용하여 구현하며, 방문한 정점을 다시 방문하지 않도록 visited 배열로 관리한다.

import java.util.LinkedList;
import java.util.Queue;

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

// * 인접 리스트 (Adjacency List)
// 그래프에서 각 정점이 어떤 정점들과 연결되어 있는지를 저장하는 방식이다
// 정점마다 "연결된 이웃 정점들의 목록"을 따로 들고 있는 형태다
//
// 예를 들어 1-2, 1-3, 2-4 로 연결되어 있다면 아래처럼 저장된다
//   1 -> [2, 3]
//   2 -> [1, 4]
//   3 -> [1]
//   4 -> [2]
//
// 비교) 인접 행렬(Adjacency Matrix)은 N x N 2차원 배열로 모든 정점 쌍의 연결 여부를 저장한다
//      연결이 적은(희소한) 그래프에서는 인접 리스트가 메모리를 훨씬 아낄 수 있다
//      (실제 연결된 간선만 저장하기 때문이다)

// 그래프를 인접 리스트(Adjacency List) 방식으로 표현하는 클래스다
// 정점마다 연결된 다른 정점들을 LinkedList에 담아 관리한다
class Graph {
    // adjacencyList[i] 는 정점 i 와 연결된 정점들의 목록이다
    private LinkedList<Integer>[] adjacencyList;

    public Graph(int vertex) {
        // 정점 번호를 1번부터 쓰기 위해 크기를 vertex + 1 로 잡는다 (0번 인덱스는 사용하지 않는다)
        adjacencyList = new LinkedList[vertex + 1];

        // 각 정점마다 빈 인접 리스트를 만들어 초기화한다
        for (int i = 0; i < adjacencyList.length; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    // 인접 리스트 전체를 반환한다 (BFS 탐색에서 정점의 이웃을 찾을 때 사용한다)
    public LinkedList<Integer>[] getAdjacencyList() {
        return adjacencyList;
    }

    // 간선(edge) 추가 - 정점 v 와 w 를 서로 연결한다
    public void addEdge(int v, int w) {
        // 무방향 그래프이므로 양쪽 모두에 서로를 추가한다
        adjacencyList[v].add(w); // v 의 이웃에 w 를 넣는다
        adjacencyList[w].add(v); // w 의 이웃에 v 를 넣는다
    }

    // 그래프 전체를 정점별로 출력한다
    public void printGraph() {
        for (int i = 1; i < adjacencyList.length; i++) {
            System.out.print("Vertex " + i + " : ");
            // 정점 i 와 연결된 이웃 정점들을 차례대로 출력한다
            for (Integer v : adjacencyList[i]) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}

public class A_collections_queue_bfs {

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

        // BFS 시작 - 1번 정점부터 탐색을 시작한다
        int startVertex = 1;
        Queue<Integer> queue = new LinkedList<>(); // 앞으로 방문할 정점을 담아두는 큐다

        // 시작 정점을 먼저 방문 처리하고 큐에 넣는다
        visited[startVertex] = true;
        queue.add(startVertex);

        System.out.println("정점 " + startVertex + "에서 시작하는 BFS");

        // 큐가 빌 때까지 반복한다 (더 이상 방문할 정점이 없으면 종료된다)
        while (queue.size() != 0) {
            int vertex = queue.poll(); // 큐의 맨 앞에서 정점을 하나 꺼낸다 (먼저 들어온 것이 먼저 나온다)
            System.out.print(vertex + " "); // 꺼낸 정점을 방문 순서대로 출력한다

            // 현재 정점과 연결된 이웃 정점들을 하나씩 확인한다
            for (int adj : graph.getAdjacencyList()[vertex]) {
                // 아직 방문하지 않은 이웃이라면 방문 처리 후 큐에 넣는다
                if (!visited[adj]) {
                    visited[adj] = true; // 중복 방문을 막기 위해 미리 방문 표시를 한다
                    queue.add(adj);      // 다음에 탐색하도록 큐에 추가한다
                }
            }
        }

    }
}
