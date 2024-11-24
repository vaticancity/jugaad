import java.util.*;

class AONode {
    String name;                     // Name of the node
    boolean isAndNode;               // If true, this is an AND node
    Map<AONode, Integer> children;   // Children and their respective costs

    public AONode(String name, boolean isAndNode) {
        this.name = name;
        this.isAndNode = isAndNode;
        this.children = new HashMap<>();
    }

    public void addChild(AONode child, int cost) {
        children.put(child, cost);
    }
}

public class AOStar {
    private Set<AONode> solvedNodes = new HashSet<>(); // Keep track of solved nodes
    private Map<AONode, Integer> costs = new HashMap<>(); // Stores the cost of each node

    public int aoStar(AONode node) {
        // If node is already solved, return its cost
        if (solvedNodes.contains(node)) {
            return costs.get(node);
        }

        // If this is a leaf node (no children), mark as solved with cost 0
        if (node.children.isEmpty()) {
            solvedNodes.add(node);
            costs.put(node, 0);
            return 0;
        }

        int minCost = Integer.MAX_VALUE;

        // Evaluate children for OR or AND nodes
        if (node.isAndNode) {
            int totalCost = 0;
            for (Map.Entry<AONode, Integer> entry : node.children.entrySet()) {
                AONode child = entry.getKey();
                int childCost = aoStar(child); // Recursive call
                totalCost += childCost + entry.getValue(); // Add edge cost
            }
            minCost = totalCost;
        } else {
            // For OR nodes, find the minimum cost path
            for (Map.Entry<AONode, Integer> entry : node.children.entrySet()) {
                AONode child = entry.getKey();
                int cost = aoStar(child) + entry.getValue(); // Add edge cost
                minCost = Math.min(minCost, cost);
            }
        }

        // Mark the current node as solved with its calculated cost
        solvedNodes.add(node);
        costs.put(node, minCost);
        return minCost;
    }

    public static void main(String[] args) {
        // Create nodes
        AONode start = new AONode("Start", false); // OR node
        AONode a = new AONode("A", false);         // OR node
        AONode b = new AONode("B", true);          // AND node
        AONode c = new AONode("C", false);         // OR node
        AONode d = new AONode("D", false);         // OR node

        // Connect nodes with edges and their costs
        start.addChild(a, 1);
        start.addChild(b, 2);
        a.addChild(c, 3);
        b.addChild(c, 1);
        b.addChild(d, 2);
        c.addChild(d, 1);

        // Run AO* algorithm
        AOStar solver = new AOStar();
        int optimalCost = solver.aoStar(start);

        System.out.println("Optimal Cost: " + optimalCost);
    }
}
