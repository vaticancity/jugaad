import java.util.*;

public class bfsPractice {
  
    public static void bfstraversal(int totalNodes, int graph[][], int startNode){
      Queue<Integer> queue=new LinkedList<>();
      boolean visited[]=new boolean[totalNodes];
      
      queue.add(startNode);
      visited[startNode]=true;
      
      while(!queue.isEmpty()){
        int currentNode=queue.remove();
        System.out.print(currentNode+" ");
        
        for(int i=0;i<totalNodes;i++){
          if(graph[currentNode][i]==1 && !visited[i]){
            queue.add(i);
            visited[i]=true;
          }
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
      
      bfstraversal(totalNodes,graph,0);
      
  }
}