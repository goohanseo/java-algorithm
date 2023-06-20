import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int d = scanner.nextInt();
            graph.get(u).add(new Node(v, d));
            graph.get(v).add(new Node(u, d));
        }

        int minMaxDistance = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] dist = dijkstra(i, N, graph);
            int maxDistance = Arrays.stream(dist).max().getAsInt();
            minMaxDistance = Math.min(minMaxDistance, maxDistance);
        }

        System.out.println(minMaxDistance);
    }

    public static int[] dijkstra(int start, int N, List<List<Node>> graph) {
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            for (Node neighbor : graph.get(node.id)) {
                int newDist = dist[node.id] + neighbor.dist;
                if (newDist < dist[neighbor.id]) {
                    dist[neighbor.id] = newDist;
                    pq.add(new Node(neighbor.id, newDist));
                }
            }
        }

        return dist;
    }

    static class Node implements Comparable<Node> {
        int id;
        int dist;

        public Node(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }

        public int compareTo(Node other) {
            return this.dist - other.dist;
        }
    }
}
