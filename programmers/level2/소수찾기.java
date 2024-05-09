import java.util.*;

class Solution {
    char[] digits;
    int size = 0, answer = 0;
    Set<Integer> nums = new HashSet<>();
    public int solution(String numbers) {
        digits = numbers.toCharArray();        
        size = digits.length;
        
        for (int i = 1; i <= size; i++) {
            dfs(i, new boolean[size], new int[i], 0);
        }
        
        return answer;
    }
    
    private void dfs(int targetLen, boolean[] used, int[] sel, int depth) {
        if (depth == targetLen) {
            int num = getNum(targetLen, sel);
            if (isPrime(num) && !nums.contains(num)) {
                System.out.println();
                answer++;
                nums.add(num);
            }
            return;
        }
        
        for (int i = 0; i < size; i++) {
            if (used[i]) continue;
            used[i] = true;
            sel[depth] = i;
            dfs(targetLen, used, sel, depth+1);
            used[i] = false;
        }
    }
    
    private int getNum(int targetLen, int[] sel) {
        String str = "";
        for (int s: sel) {
            str += digits[s];
        }
        return Integer.parseInt(str);
    }
    
    private boolean isPrime(int n) {
        if (n == 0 || n == 1) return false;
        for (int i = 2; i<=(int) Math.sqrt(n); i++) {
          if (n % i == 0) {
              return false;
          }
        }
        return true;
    }
}
