import java.util.*;

class Solution {
    int[] parent;
    Queue<Edge> q = new PriorityQueue<>();
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];
        int cnt = 0;
        
        for (int i = 1; i < n; i++) parent[i] = i;
        for (int[] cost : costs) q.add(new Edge(cost));
        
        while(!q.isEmpty() && cnt < n - 1) {
            Edge e = q.poll();
            if(!union(e.start, e.end)) continue;

            cnt++;
            answer += e.cost;
        }
        
        return answer;
    }
    
    private boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        
        if (pa == pb) return false;
        parent[pa] = pb;
        return true;
    }
    
    private int find(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }
    
    
    class Edge implements Comparable<Edge> {
        int start, end, cost;
        
        public Edge(int[] cost) {
            this.start = cost[0];
            this.end = cost[1];
            this.cost = cost[2];
        }
        
        @Override
        public int compareTo(Edge e) {
            return this.cost - e.cost;
        }
    }
}
