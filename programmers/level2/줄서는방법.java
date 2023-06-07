import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];

        List<Integer> nums = new ArrayList<>();
        int div = 1;

        for (int i = 1; i <= n; i++) {
            nums.add(i);
            div *= i;
        }

        div /= n;
        k--;
        for (int i = 0; i < n - 1; i++) {
            int index = (int)((k / div) % nums.size());
            answer[i] = nums.remove(index);
            k %= div;
            div /= (n - i - 1);
        }
        answer[n-1] = nums.get(0);

        return answer;
    }
}
