import java.util.*;

class Solution {
    boolean[] isDeleted;
    Deque<Integer> stk = new ArrayDeque<>();
    int size;
    public String solution(int n, int k, String[] cmd) {
        isDeleted = new boolean[n];
        size = n;
        int curr = k;
        
        for (String c : cmd) {
            char action = c.charAt(0);
            int cnt = c.length() < 2 ? 0 : Integer.parseInt(c.substring(2));
            
            if (action == 'U') {
                curr = getNextIdx(curr, cnt, -1);
                continue;
            }
            
            if (action == 'D') {
                curr = getNextIdx(curr, cnt, 1);
                continue;
            }
            
            if (action == 'C') {
                isDeleted[curr] = true;
                stk.push(curr);
                int next = getNextIdx(curr, 1, 1);
                curr = next >= n? getNextIdx(curr, 1, -1) : next;
                continue;
            }
            
            isDeleted[stk.pop()] = false;
        }
        
        StringBuilder sb = new StringBuilder();
        for (Boolean d : isDeleted) {
            if (d) sb.append('X');
            else sb.append('O');
        }
        return sb.toString();
    }
    
    private int getNextIdx(int start, int cnt, int dir) {
        int use = cnt;
        int curr = start;
        
        while (use > 0) {
            curr += dir;
            if (curr < 0 || curr >= size) break;
            if (isDeleted[curr] == false) use--;
        }
        
        return curr;
    }
}
