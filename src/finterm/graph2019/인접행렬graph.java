package finterm.graph2019;


import java.util.*;
import java.io.*;

public class 인접행렬graph {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input1.txt"));
        int N = Integer.parseInt(br.readLine().trim());

        // Initialize adjacency matrix and weight sum array
        int[][] graph = new int[N][N];
        int[] weightSum = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] != 0) {
                    weightSum[i] += graph[i][j];
                }
            }
        }

        // Modified Floyd-Warshall algorithm to find connected components
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (graph[i][k] != 0 && graph[k][j] != 0) {
                        if (graph[i][j] == 0) {
                            graph[i][j] = graph[i][k] + graph[k][j];
                            weightSum[i] += graph[i][j];
                        } else {
                            graph[i][j] = Math.max(graph[i][j], graph[i][k] + graph[k][j]);
                            weightSum[i] = Math.max(weightSum[i], weightSum[k]);
                        }
                    }
                }
            }
        }

        // Print the maximum weight sum
        int maxWeightSum = Arrays.stream(weightSum).max().getAsInt();
        System.out.println("The maximum sum of weights in a connected component is: " + maxWeightSum);
    }
}
