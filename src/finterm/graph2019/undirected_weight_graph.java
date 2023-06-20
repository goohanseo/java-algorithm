package finterm.graph2019;

import java.io.*;
import java.util.*;

class undirected_weight_graph {
    static int parent[], weights[];

    static int find(int x) {
        if(parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int x, int y, int weight) {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX != rootY) {
            parent[rootY] = rootX;
            weights[rootX] += weights[rootY] + weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input1.txt"));
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line, " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        weights = new int[N+1];

        for(int i = 0; i <= N; i++) {
            parent[i] = i;
            weights[i] = 0;
        }

        int maxWeight = 0;
        for(int i = 0; i < M; i++) {
            line = br.readLine();
            st = new StringTokenizer(line, " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            union(u, v, w);
            maxWeight = Math.max(maxWeight, weights[find(u)]);
        }

        System.out.println("The maximum sum of weights in a connected component is: " + maxWeight);
    }
}

