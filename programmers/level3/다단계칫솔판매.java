import java.util.*;

class Solution {
    Map<String, String> parents = new HashMap<>();
    Map<String, Integer> money = new HashMap<>();
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int N = enroll.length;
        int[] answer = new int[N];
        
        for (int i = 0; i < N; i++) {
            parents.put(enroll[i], referral[i]);
        }
        
        for (int i = 0; i < seller.length; i++) {
            sell(seller[i], amount[i] * 100);
        }
        
        for (int i = 0; i < N; i++) {
            answer[i] = money.getOrDefault(enroll[i], 0);
        }
        
        return answer;
    }
    
    private void sell(String name, int count) {
        String parent = parents.get(name);
        
        int giveMoney = (int)(count * 0.1);
        money.put(name, money.getOrDefault(name, 0) + (count - giveMoney));
        
        if (giveMoney >= 1) {
            sell(parent, giveMoney);
        }
    }
}
