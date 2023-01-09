import java.io.*;
import java.util.*;

public class Main {
    static int INF = Integer.MAX_VALUE;
    static int V, E, K;
    static int[] dis;
    static List<Edge>[] edges;
    static PriorityQueue<Edge> q;

    public static void main(String [] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ", false);

        V = Integer.parseInt(st.nextToken());   // 정점
        E = Integer.parseInt(st.nextToken());   // 간선
        K = Integer.parseInt(br.readLine());    // 시작


        q = new PriorityQueue<>(Comparator.comparing(Edge::getDistance));
        dis = new int[V+1];
        edges = new ArrayList[V+1];

        for (int i = 0; i <= V; i++) {
            dis[i] = INF;
            edges[i] = (new ArrayList<Edge>());
        }

        dis[K] = 0;
        q.add(new Edge(K, 0));

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ", false);
            int u = Integer.parseInt(st.nextToken()); // 시작
            int v = Integer.parseInt(st.nextToken()); // 도착
            int w = Integer.parseInt(st.nextToken()); // 가중치

            edges[u].add(new Edge(v, w));
        }

        while(!q.isEmpty()) {
            Edge currEdge = q.poll();

            for (int i = 0; i <edges[currEdge.node].size(); i++) {
                Edge nextEdge = edges[currEdge.node].get(i);

                if(dis[nextEdge.node] <= dis[currEdge.node] + nextEdge.distance) continue;
                else {
                    dis[nextEdge.node] = dis[currEdge.node] + nextEdge.distance;
                    q.add(new Edge(nextEdge.node, dis[nextEdge.node]));
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if ( dis[i] == INF) System.out.println("INF");
            else System.out.println(dis[i] + "");
        }
    }

    static class Edge {
        int node;
        int distance;

        public Edge(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        public int getNode() {
            return node;
        }

        public int getDistance() {
            return distance;
        }
    }
}
