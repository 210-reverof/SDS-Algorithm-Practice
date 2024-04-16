import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int INF = 1000000;
        int[] answer = new int[sources.length];
        List<Integer>[] graph = new List[n+1];
        Queue<int[]> q = new LinkedList<>();
        
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        
        for (int[] road: roads) {
            int a = road[0];
            int b = road[1];
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        for (int i = 0; i < sources.length; i++) {
            int src = sources[i];
            if (src == destination) {
                answer[i] = 0;
                continue;
            }
            boolean[] visited = new boolean[n+1];
            visited[src] = true;
            q.add(new int[]{src, 0});
            
            boolean isEnd = false;
            while(!q.isEmpty() && !isEnd) {
                int[] curr = q.poll();
    
                for (int next: graph[curr[0]]) {
                    if (next == destination) {
                        answer[i] = curr[1] + 1;
                        isEnd = true;
                        break;
                    }
                    if (visited[next]) continue;
                    visited[next] = true;
                    q.add(new int[]{next, curr[1] + 1});
                }
            }
            q.clear();
            if (answer[i] == 0) answer[i] = -1;
        }
        
        return answer;
    }
}
