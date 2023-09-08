import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int n : tangerine) countMap.put(n, countMap.getOrDefault(n, 0) + 1);
        
        Queue<Tang> q = new PriorityQueue<>();
        for (Integer key : countMap.keySet()) {
            q.add(new Tang(key, countMap.get(key)));
        }
        
        while(!q.isEmpty()) {
            if (k <= 0) break;
            answer++;
            k -= q.poll().count;            
        }
        
        return answer;
    }
}

    class Tang implements Comparable<Tang> {
        int num, count;
        
        public Tang(int num, int count) {
            this.num = num;
            this.count = count;
        }
        
        @Override
        public int compareTo(Tang t) {
            return t.count - this.count;
        } 
    }
