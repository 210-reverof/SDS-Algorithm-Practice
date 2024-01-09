import java.util.*;

public class Solution {

    public int solution(int N, int[][] road, int K) {
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] r : road) {
            int a = r[0];
            int b = r[1];
            int cost = r[2];
            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
        }

        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.cost > distance[cur.city]) {
                continue;
            }

            for (Node next : graph.get(cur.city)) {
                int newCost = cur.cost + next.cost;

                if (newCost < distance[next.city]) {
                    distance[next.city] = newCost;
                    pq.offer(new Node(next.city, newCost));
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (distance[i] <= K) {
                answer++;
            }
        }

        return answer;
    }
    
    class Node implements Comparable<Node> {
        int city, cost;

        public Node(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }
}
