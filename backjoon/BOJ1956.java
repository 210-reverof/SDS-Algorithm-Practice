import java.util.*;
import java.io.*;

public class BOJ1956 {

    static int V, E;
    static int[][] graph;
    static final int INF = 987000000;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new int[V+1][V+1];

        for (int i = 1; i <= V; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a][b] = c;
        }

        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        int min = INF;
        for (int i = 1; i <= V; i++) {
            for (int j = i+1; j<= V; j++) {
              if (graph[i][j] == INF || graph[j][i] == INF) continue;
              min = Math.min(min, graph[i][j] + graph[j][i]);
            }
        }

        System.out.println(min == INF? -1 : min);
    }
}
