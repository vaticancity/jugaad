import java.util.Scanner;

public class IterativeDeepeningDFS  {
    static int distance = 0;
    private int[][] adjacencyMatrix;
    private int numberOfVertices;

    public IterativeDeepeningDFS(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.numberOfVertices = adjacencyMatrix.length;
    }

    public void IDDFS(int startVertex, int targetVertex) {
        for (int depth = 0; depth < numberOfVertices; depth++) {

            boolean[] visited = new boolean[numberOfVertices];
            System.out.println("Searching at depth: " + depth);
            boolean found = depthLimitedDFS(startVertex, targetVertex, depth, visited);

            if (found) {
                System.out.println("Target vertex " + targetVertex + " found at depth: " + depth);
                return;
            }
        }

        System.out.println("Target vertex " + targetVertex + " not found within the maximum depth.");
    }

    private boolean depthLimitedDFS(int currentVertex, int targetVertex, int depthLimit, boolean[] visited) {
        System.out.println("Checking vertex " + currentVertex + " with depth limit " + depthLimit);
        if (currentVertex == targetVertex) {
            return true;
        }
        if (depthLimit <= 0) {
            return false;
        }

        visited[currentVertex] = true;

        // Recur for all adjacent vertices
        for (int i = 0; i < numberOfVertices; i++) {
            if (adjacencyMatrix[currentVertex][i] == 1 && !visited[i]) {
                System.out.println("Moving to vertex " + i);
                if (depthLimitedDFS(i, targetVertex, depthLimit - 1, visited)) {
                    // recursive call on the neighborung noce with depth-1
                    return true;
                }
            }
        }
        return false; // Target vertex not found
    }

    public static void main(String[] args) {
        int[][] adjacencyMatrix = {
            {0, 1, 1, 0, 0, 0},  
            {1, 0, 0, 1, 0, 0},  
            {1, 0, 0, 0, 1, 0},  
            {0, 1, 0, 0, 0, 1}, 
            {0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0}
        };

        IterativeDeepeningDFS iddfs = new IterativeDeepeningDFS(adjacencyMatrix);
        int startVertex;
        int targetVertex;

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the start vertex: ");
        startVertex = sc.nextInt();
        System.out.println("Please enter the goal vertex: ");
        targetVertex = sc.nextInt();

        sc.close();

        iddfs.IDDFS(startVertex, targetVertex);
    }
}