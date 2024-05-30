import java.util.*;

class Solution {
    Map<Integer, Integer> in = new HashMap<>();
    Map<Integer, Integer> out = new HashMap<>();
    Set<Integer> nums = new HashSet<>();
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        for (int[] edge: edges) {
            out.put(edge[0], out.getOrDefault(edge[0], 0) + 1);
            in.put(edge[1], in.getOrDefault(edge[1], 0) + 1);
            
            nums.add(edge[0]);
            nums.add(edge[1]);
        }
        
        for (int num: nums) {
            int inCnt = in.getOrDefault(num, 0);
            int outCnt = out.getOrDefault(num, 0);
            
            if (inCnt == 0 && outCnt >= 2) {
                answer[0] = num;
            }
            
            else if (inCnt >= 1 && outCnt == 0) {
                answer[2]++;
            }
            
            else if (inCnt >= 2 && outCnt >= 2) {
                answer[3]++;
            }
        }    
        
        answer[1] = out.get(answer[0]) - (answer[2] + answer[3]);
        return answer;
    }
}
