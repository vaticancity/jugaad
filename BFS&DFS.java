import java.util.*;

public class GraphTraversal {

    // BFS Traversal Method
    public static void bfsTraversal(int totalNodes, int graph[][], int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        boolean visited[] = new boolean[totalNodes];

        queue.add(startNode);
        visited[startNode] = true;

        System.out.print("BFS: ");
        while (!queue.isEmpty()) {
            int currentNode = queue.remove();
            System.out.print(currentNode + " ");

            for (int i = 0; i < totalNodes; i++) {
                if (graph[currentNode][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
        System.out.println();
    }

    // DFS Traversal Method
    public static void dfsTraversal(int graph[][], int totalNodes, int currentNode, boolean visited[]) {
        visited[currentNode] = true;
        System.out.print(currentNode + " ");

        for (int neighbour = 0; neighbour < totalNodes; neighbour++) {
            if (graph[currentNode][neighbour] == 1 && !visited[neighbour]) {
                dfsTraversal(graph, totalNodes, neighbour, visited);
            }
        }
    }

    public static void main(String[] args) {
        int totalNodes = 5;

        // Graph Adjacency Matrix (First BFS Example)
        int graph[][] = {
            {0, 1, 0, 0, 1}, // Node 0
            {1, 0, 1, 1, 0}, // Node 1
            {0, 1, 0, 0, 0}, // Node 2
            {0, 1, 0, 0, 1}, // Node 3
            {1, 0, 0, 1, 0}  // Node 4
        };

        // BFS Traversal
        bfsTraversal(totalNodes, graph, 0);

        // DFS Traversal
        boolean visited[] = new boolean[totalNodes];
        System.out.print("DFS: ");
        dfsTraversal(graph, totalNodes, 0, visited);
        System.out.println();
    }
}
