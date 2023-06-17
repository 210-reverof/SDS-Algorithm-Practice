import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Deque<Integer> s = new ArrayDeque();

        for (int i = 0; i < prices.length; i++) {
            while (!s.isEmpty() && prices[i] < prices[s.peek()]) {
                answer[s.peek()] = i - s.peek();
                s.pop();
            }
            s.push(i);
        }

        while (!s.isEmpty()) {
            answer[s.peek()] = prices.length - s.peek() - 1;
            s.pop();
        }

        return answer;
    }

}
