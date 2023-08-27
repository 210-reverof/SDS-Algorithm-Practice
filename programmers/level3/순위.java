import java.util.*;

class Solution {
    int size;
    Deque<Integer> stack = new ArrayDeque<>();
    public int solution(int n, int[][] results) {
        int answer = 0;
        size = n;
        List<Integer>[] stronger = new List[n+1];
        List<Integer>[] weaker = new List[n+1];
        for (int i = 1; i <= n; i++) {
            stronger[i] = new ArrayList<>();
            weaker[i] = new ArrayList<>();
        }
        
        for (int[] res : results) {
            stronger[res[0]].add(res[1]);
            weaker[res[1]].add(res[0]);
        }
        
        for (int i = 1; i <= n; i++) {
            if (getNextCnt(stronger, i) + getNextCnt(weaker, i) == n-1) answer++;
        }
        
        return answer;
    }
    
    private int getNextCnt(List<Integer>[] edges, int n) {
        int res = 0;
        boolean[] visited = new boolean[size+1];
        visited[n] = true;
        stack.push(n);
        
        while(!stack.isEmpty()) {
            for (Integer next : edges[stack.pop()]) {
                if (visited[next]) continue;
                res++;
                visited[next] = true;
                stack.push(next);
            }
        }
        
        return res;
    }
}
