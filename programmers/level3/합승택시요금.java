import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] graph = new int[n][n];
        int INF = 10000000;
        
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
		}
        
        for (int[] fare: fares) {
            int start = fare[0] - 1;
            int end = fare[1] - 1;
            graph[start][end] = fare[2];
            graph[end][start] = fare[2];
        }
        
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
        
        int answer = graph[s-1][a-1] + graph[s-1][b-1];
        for (int i = 0; i < n; i++) {
            if (i != s-1 && graph[s-1][i] != INF && graph[a-1][i] != INF && graph[b-1][i] != INF) {
                answer = Math.min(answer, graph[s-1][i] + graph[i][b-1] + graph[i][a-1]);
            }
        }
        
        return answer;
    }
}
