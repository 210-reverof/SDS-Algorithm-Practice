import java.util.*;

class Solution {
    Map<String, List<String>> tree = new HashMap<>();
    Map<String, Integer> money = new HashMap<>();
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int N = enroll.length;
        int[] answer = new int[N];
        tree.put("-", new LinkedList<>());
        
        for (int i = 0; i < N; i++) {
            tree.put(enroll[i], new LinkedList<>());
            tree.get(referral[i]).add(enroll[i]);
        }
        
        for (int i = 0; i < seller.length; i++) {
            money.put(seller[i], amount[i] * 100);
        }
        
        dfs("-");
        
        for (int i = 0; i < N; i++) {
            answer[i] = money.getOrDefault(enroll[i], 0);
        }
        
        return answer;
    }
    
    private int dfs(String key) {
        List<String> childs = tree.get(key);
        int sum = money.getOrDefault(key, 0);
        
        int giveSum = (int)(sum * 0.1);
        for (String child: childs) {
            int currSum = dfs(child);
            sum += currSum;
            giveSum += (int)(currSum * 0.1);
        }
        money.put(key, sum - giveSum);
        
        return giveSum;
    }
}
