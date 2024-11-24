import java.util.*;

public class dfspractice {
  
    public static void dfsTraversal(int graph[][], int totalNodes, int currentNode, boolean visited[] ){
      visited[currentNode]=true;
      System.out.print(currentNode+" ");
      
      for(int neighbour=0; neighbour<totalNodes; neighbour++){
        if(graph[currentNode][neighbour]==1 && !visited[neighbour]){
          dfsTraversal(graph,totalNodes,neighbour,visited);
        }
      }
    }
    
    public static void main(String[] args) {
      int totalNodes=5;
      int graph[][]=new int[totalNodes][totalNodes];
      
        graph[0][1] = 1;
        graph[0][4] = 1;
        graph[1][0] = 1;
        graph[1][2] = 1;
        graph[1][3] = 1;
        graph[2][1] = 1;
        graph[3][1] = 1;
        graph[3][4] = 1;
        graph[4][0] = 1;
        graph[4][3] = 1;
        
        boolean visited[]=new boolean[totalNodes];
        
        
        dfsTraversal(graph,totalNodes,0,visited);
  }
}