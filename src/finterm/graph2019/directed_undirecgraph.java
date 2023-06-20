package finterm.graph2019;


import java.util.*;
import java.io.*;

public class directed_undirecgraph {
    static ArrayList<ArrayList<Integer>> graph;
    static ArrayList<ArrayList<Integer>> reverseGraph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input2.txt"));
        int N = Integer.parseInt(br.readLine().trim());

        // Initialize graph and reverseGraph
        graph = new ArrayList<>();
        reverseGraph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        // Read the adjacency matrix and create the graph and reverseGraph
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().trim().split(" ");
            for (int j = 0; j < N; j++) {
                int edge = Integer.parseInt(line[j]);
                if (edge == 1) {
                    graph.get(i).add(j);
                    reverseGraph.get(j).add(i);
                }
            }
        }

        // DFS from vertex 0 in original graph
        boolean[] visited = new boolean[N];
        DFS(0, visited, graph);

        for (boolean visit : visited) {
            if (!visit) {
                System.out.println("NO");
                return;
            }
        }

        // DFS from vertex 0 in reverse graph
        visited = new boolean[N];
        DFS(0, visited, reverseGraph);

        for (boolean visit : visited) {
            if (!visit) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    public static void DFS(int node, boolean[] visited, ArrayList<ArrayList<Integer>> g) {
        visited[node] = true;
        for (int child : g.get(node)) {
            if (!visited[child]) {
                DFS(child, visited, g);
            }
        }
    }
}
