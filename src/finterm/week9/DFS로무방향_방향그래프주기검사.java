package finterm.week9;

import java.util.*;

public class DFS로무방향_방향그래프주기검사 {
    private int numVertices;
    private LinkedList<Integer> adjList[];

    DFS로무방향_방향그래프주기검사(int vertices) {
        numVertices = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; ++i) {
            adjList[i] = new LinkedList<>();
        }
    }

    void addEdge(int v, int w) {
        adjList[v].add(w);
        adjList[w].add(v);
    }

    boolean isCyclicUtil(int v, boolean visited[], int parent) {
        visited[v] = true;
        Integer i;

        for (Integer node : adjList[v]) {
            i = node;

            if (!visited[i]) {
                if (isCyclicUtil(i, visited, v))
                    return true;
            } else if (i != parent)
                return true;
        }
        return false;
    }

    boolean isCyclic() {
        boolean visited[] = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++)
            if (!visited[i] && isCyclicUtil(i, visited, -1))
                return true;

        return false;
    }

    public static void main(String args[]) {
        DFS로무방향_방향그래프주기검사 graph = new DFS로무방향_방향그래프주기검사(5);
        graph.addEdge(1, 0);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        if (graph.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contains cycle");

        graph = new DFS로무방향_방향그래프주기검사(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        if (graph.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contains cycle");
    }
}
