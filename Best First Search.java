import java.util.*;

public class BestFirstSearchMatrix {

    private final int[][] adjMatrix;
    private final int numberOfVertices;

    public BestFirstSearchMatrix(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        adjMatrix = new int[numberOfVertices][numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            Arrays.fill(adjMatrix[i], Integer.MAX_VALUE); // Infinity
        }
    }

    public void addEdge(int u, int v, int cost) {
        adjMatrix[u][v] = cost;
        adjMatrix[v][u] = cost; // Undirected graph
    }

    public void bestFirstSearch(int source, int target) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[numberOfVertices];
        int[] parent = new int[numberOfVertices];
        Arrays.fill(parent, -1);

        pq.add(new Vertex(source, 0));

        System.out.print("Visited: ");
        while (!pq.isEmpty()) {
            Vertex current = pq.poll();
            int currentVertex = current.vertex;

            // Print visited node
            System.out.print(currentVertex + " -> ");

            if (currentVertex == target) {
                break;
            }

            // Mark as visited
            if (visited[currentVertex]) {
                continue;
            }
            visited[currentVertex] = true;

            // Explore neighbors
            for (int i = 0; i < numberOfVertices; i++) {
                if (!visited[i] && adjMatrix[currentVertex][i] != Integer.MAX_VALUE) {
                    pq.add(new Vertex(i, adjMatrix[currentVertex][i]));
                    parent[i] = currentVertex;
                }
            }
        }

        // Reconstruct the path
        System.out.println("END");
        List<Integer> path = new ArrayList<>();
        int current = target;
        while (current != -1) {
            path.add(current);
            current = parent[current];
        }
        Collections.reverse(path);
        System.out.println("Path: " + path);
    }

    static class Vertex implements Comparable<Vertex> {
        int vertex;
        int cost;

        Vertex(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Vertex other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static void main(String[] args) {
        int numberOfVertices = 14;

        BestFirstSearchMatrix graph = new BestFirstSearchMatrix(numberOfVertices);

        graph.addEdge(0, 1, 3);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 3, 4);
        graph.addEdge(1, 4, 6);
        graph.addEdge(2, 4, 2);
        graph.addEdge(3, 5, 7);
        

        int source = 0;
        int target = 5;

        graph.bestFirstSearch(source, target);
    }
}
