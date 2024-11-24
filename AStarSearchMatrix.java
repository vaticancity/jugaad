import java.util.*;

public class AStarSearchMatrix {

    private final int[][] adjMatrix;
    private final int numberOfVertices;

    public AStarSearchMatrix(int numberOfVertices) {
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

    public void aStarSearch(int source, int target, int[] heuristic) {
        // Priority Queue ordered by f-cost (f = g + h)
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[numberOfVertices];
        int[] parent = new int[numberOfVertices];
        int[] gCost = new int[numberOfVertices]; // Stores g-cost for each vertex
        Arrays.fill(parent, -1);
        Arrays.fill(gCost, Integer.MAX_VALUE);
        
        // Set the g-cost for the source to 0
        gCost[source] = 0;

        // Add the source node with its f-cost (f = g + h)
        pq.add(new Vertex(source, heuristic[source]));

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
                    int newGCost = gCost[currentVertex] + adjMatrix[currentVertex][i];

                    // If a shorter path is found
                    if (newGCost < gCost[i]) {
                        gCost[i] = newGCost;
                        parent[i] = currentVertex;
                        int fCost = gCost[i] + heuristic[i];
                        pq.add(new Vertex(i, fCost));
                    }
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

        // Print total cost (g-cost of the target)
        System.out.println("Total cost: " + gCost[target]);
    }

    static class Vertex implements Comparable<Vertex> {
        int vertex;
        int fCost;

        Vertex(int vertex, int fCost) {
            this.vertex = vertex;
            this.fCost = fCost;
        }

        @Override
        public int compareTo(Vertex other) {
            return Integer.compare(this.fCost, other.fCost);
        }
    }

    public static void main(String[] args) {
        int numberOfVertices = 14;

        AStarSearchMatrix graph = new AStarSearchMatrix(numberOfVertices);

        // Adding edges
        graph.addEdge(0, 1, 3);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 3, 4);
        graph.addEdge(1, 4, 6);
        graph.addEdge(2, 4, 2);
        graph.addEdge(3, 5, 7);

        int source = 0;
        int target = 5;

        // Heuristic values for each vertex (arbitrary for this example)
        // The heuristic values should be designed based on the problem, representing the estimated cost from each vertex to the goal.
        int[] heuristic = new int[numberOfVertices];
        Arrays.fill(heuristic, Integer.MAX_VALUE); // Initialize with infinity
        heuristic[5] = 0; // Set the heuristic of the goal to 0 (since we are at the goal node)
        
        // In a real scenario, you'd have more realistic heuristic values here
        // Example heuristic values (assuming straight-line distances, etc.):
        heuristic[0] = 10;
        heuristic[1] = 7;
        heuristic[2] = 6;
        heuristic[3] = 4;
        heuristic[4] = 3;

        graph.aStarSearch(source, target, heuristic);
    }
}