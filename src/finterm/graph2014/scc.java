package finterm.graph2014;

import java.util.List;
import java.util.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

    public class scc {
        static int V, E;
        static List<List<Integer>> graph, reverseGraph, SCC;
        static boolean[] visited;
        static Stack<Integer> stack;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = stoi(st.nextToken());
            E = stoi(st.nextToken());

            graph = new ArrayList<>();
            reverseGraph = new ArrayList<>();
            SCC = new ArrayList<>();
            stack = new Stack<>();
            visited = new boolean[V + 1];

            for (int i = 0; i < V + 1; i++) {
                graph.add(new ArrayList<>());
                reverseGraph.add(new ArrayList<>());
            }

            // input
            int from, to;
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                from = stoi(st.nextToken());
                to = stoi(st.nextToken());

                graph.get(from).add(to);
                reverseGraph.get(to).add(from);
            }

            // (1)
            for (int i = 1; i <= V; i++) {
                if (!visited[i]) {
                    // (2)
                    DFS(i);
                }
            }

            Arrays.fill(visited, false);

            // (3)
            int sccNum = 0;
            SCC.add(new ArrayList<>());
            while (!stack.isEmpty()) {
                int node = stack.pop();
                if (!visited[node]) {
                    reDFS(node, sccNum);
                    sccNum++;
                    SCC.add(new ArrayList<>());
                }
            }
            SCC.remove(SCC.size() - 1);

            for (List<Integer> s : SCC) {
                Collections.sort(s);
            }

            // SCC 정렬
            Collections.sort(SCC, new Comparator<List<Integer>>() {
                @Override
                public int compare(List<Integer> o1, List<Integer> o2) {
                    return o1.get(0) - o2.get(0);
                }
            });

            // output
            StringBuilder sb = new StringBuilder();
            sb.append(SCC.size());
            sb.append('\n');
            for (List<Integer> s : SCC) {
                for (int i : s) {
                    sb.append(i + " ");
                }
                sb.append(-1);
                sb.append('\n');
            }
            System.out.println(sb.toString());
        }

        private static void reDFS(int node, int sccNum) {
            visited[node] = true;
            SCC.get(sccNum).add(node);

            for (int i : reverseGraph.get(node)) {
                if (!visited[i]) {
                    reDFS(i, sccNum);
                }
            }
        }

        private static void DFS(int node) {
            visited[node] = true;

            for (int i : graph.get(node)) {
                if (!visited[i]) {
                    DFS(i);
                }
            }
            stack.push(node);
        }

        private static int stoi(String s) {
            return Integer.parseInt(s);
        }
    }



/*8 13
1 2
1 3
2 3
2 4
2 5
3 5
3 7
3 8
4 5
5 6
6 4
7 1
7 8*/
