import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        Queue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        for (int work : works) q.add(work);
        
        while (n-- > 0 && q.peek() != 0) q.add(q.poll() - 1);
        while (!q.isEmpty() && q.peek() != 0) answer += (Math.pow(q.poll(), 2));
        
        return answer;
    }
}
