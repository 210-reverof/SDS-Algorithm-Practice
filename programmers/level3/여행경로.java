import java.util.*;

class Solution {
    Map<String, List<Edge>> edges = new HashMap<>();
    Deque<String> stk = new ArrayDeque<>();
    int tCnt = 0;
    List<String> answer = null;
    
    public List<String> solution(String[][] tickets) {
        tCnt = tickets.length;
        
        for (String[] t : tickets) {
            if (!edges.containsKey(t[0])) edges.put(t[0], new LinkedList<>());
            edges.get(t[0]).add(new Edge(t[1]));
        }
        for (String key : edges.keySet()) Collections.sort(edges.get(key));
        
        dfs("ICN", 0);
        
        return answer;
    }
    
    public void dfs(String start, int depth) {
        if (answer != null) return;

        if (depth == tCnt) {
            answer = new LinkedList<>();
            answer.add("ICN");
            while(!stk.isEmpty()) answer.add(stk.removeLast());
            return;
        }
        
        if (!edges.containsKey(start)) return;
        for (Edge next : edges.get(start)) {
            if (next.visited) continue;
            next.visited = true;
            stk.push(next.end);
            
            dfs(next.end, depth + 1);
            if (answer != null) return;
            
            next.visited = false;
            stk.pop();
        }
        
    }
    
    class Edge implements Comparable<Edge>{
        String end;
        boolean visited = false;
        
        public Edge(String end) {
            this.end = end;
        }
        
        @Override
        public int compareTo(Edge e) {
            return this.end.compareTo(e.end);
        }
    }
    
}
