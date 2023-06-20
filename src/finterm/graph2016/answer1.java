package finterm.graph2016;

import java.io.*;
import java.util.*;

class Island {
    int x, y, r;
    Island(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
}

public class answer1 {
    static int N;
    static Island[] islands;
    static int[] parent, size;

    static boolean connected(Island a, Island b) {
        int dx = a.x - b.x;
        int dy = a.y - b.y;
        int sumR = a.r + b.r;
        return dx * dx + dy * dy <= 4 * sumR * sumR;
    }

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return;
        if (size[rootA] < size[rootB]) {
            parent[rootA] = rootB;
            size[rootB] += size[rootA];
        } else {
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/Users/guhanseo/study/java-algorithm/src/finterm/graph2016/input.txt"));
        N = Integer.parseInt(br.readLine());
        islands = new Island[N];
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            islands[i] = new Island(x, y, r);
            parent[i] = i;
            size[i] = 1;
        }
        br.close();

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (connected(islands[i], islands[j])) union(i, j);
            }
        }

        int maxSize = 0;
        for (int i = 0; i < N; i++) {
            maxSize = Math.max(maxSize, size[find(i)]);
        }
        System.out.println(maxSize);
    }
}
