package finterm.graph2020;
import java.util.*;

public class Bipartite {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        List<Integer>[] adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        int[] colors = new int[N];
        Arrays.fill(colors, -1);
        for (int i = 0; i < N; i++) {
            if (colors[i] == -1) {
                if (!isBipartite(i, 0, colors, adj)) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
    }

    public static boolean isBipartite(int node, int color, int[] colors, List<Integer>[] adj) {
        colors[node] = color;
        for (int neigh : adj[node]) {
            if (colors[neigh] == -1) {
                if (!isBipartite(neigh, 1 - color, colors, adj)) {
                    return false;
                }
            } else if (colors[neigh] == color) {
                return false;
            }
        }
        return true;
    }
}
