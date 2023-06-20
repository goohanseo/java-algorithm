package finterm.week9;

import java.util.*;

public class BFS로무방향_방향그래프주기검사 {
    private int numVertices;
    private LinkedList<Integer> adjList[];

    BFS로무방향_방향그래프주기검사(int vertices) {
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

    boolean isCyclic() {
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && isCyclicUtil(i, visited, -1))
                return true;
        }
        return false;
    }

    boolean isCyclicUtil(int currentVertex, boolean[] visited, int parent) {
        visited[currentVertex] = true;
        for (int i : adjList[currentVertex]) {
            if (!visited[i]) {
                if (isCyclicUtil(i, visited, currentVertex)) {
                    return true;
                }
            } else if (i != parent) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        BFS로무방향_방향그래프주기검사 graph = new BFS로무방향_방향그래프주기검사(5);
        graph.addEdge(1, 0);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        if (graph.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contains cycle");

        graph = new BFS로무방향_방향그래프주기검사(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        if (graph.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contains cycle");
    }
}
