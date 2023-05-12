package week9;

import java.util.*;

public class DFS_NODIRECTED {
    private int numVertices;
    private LinkedList<Integer>[] adjList;

    public DFS_NODIRECTED(int numVertices) {
        this.numVertices = numVertices;
        adjList = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int v, int w) {
        adjList[v].add(w);
        adjList[w].add(v); // For undirected graph, add edge in both directions
    }

    public boolean hasCycle() {
        boolean[] visited = new boolean[numVertices];

        for (int vertex = 0; vertex < numVertices; vertex++) {
            if (!visited[vertex]) {
                if (dfs(vertex, visited, -1)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(int vertex, boolean[] visited, int parent) {
        visited[vertex] = true;

        for (int neighbor : adjList[vertex]) {
            if (!visited[neighbor]) {
                if (dfs(neighbor, visited, vertex)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        DFS_NODIRECTED graph = new DFS_NODIRECTED(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 2);

        System.out.println("Graph has cycle: " + graph.hasCycle());
    }
}
