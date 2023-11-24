import java.util.*;

class Solution {
    boolean[] isDeleted;
    int[] udp, ddp;
    Deque<Integer> stk = new ArrayDeque<>();
    int size;
    public String solution(int n, int k, String[] cmd) {
        isDeleted = new boolean[n];
        udp = new int[n];
        ddp = new int[n];
        size = n;
        int curr = k;
        
        for (int y = 0; y < cmd.length; y++) {
        // for (int y = 0; y < 4; y++) {
            String c = cmd[y];
            char action = c.charAt(0);
            int cnt = c.length() < 2 ? 0 : Integer.parseInt(c.substring(2));
            
            if (action == 'U') {
                curr = getUpIdx(curr, cnt);
                continue;
            }
            
            if (action == 'D') {
                curr = getDownIdx(curr, cnt);
                continue;
            }
            
            if (action == 'C') {
                delete(curr);
                int next = getDownIdx(curr, 1);
                curr = next == curr? getUpIdx(curr, 1) : next;
                continue;
            }
            
            rollback();
        }
        
        // System.out.println("curr : " + curr);
        // System.out.println("udp : " + Arrays.toString(udp));
        // System.out.println("ddp : " + Arrays.toString(ddp));
        StringBuilder sb = new StringBuilder();
        for (Boolean d : isDeleted) {
            if (d) sb.append('X');
            else sb.append('O');
        }
        return sb.toString();
    }
    
    private int getUpIdx(int start, int cnt) {
        int curr = start;
        for (int i = 0; i < cnt; i++) {
            if (curr-1 < 0) break;
            curr -= ddp[curr-1] + 1;
        }
        return curr; 
    }
    
    private int getDownIdx(int start, int cnt) {
        int curr = start;
        for (int i = 0; i < cnt; i++) {
            if (curr+1 >= size) break;
            curr += udp[curr+1] + 1;
        }
        return curr;
    }
    
    private void delete(int idx) {
        int curr = idx;
        isDeleted[curr] = true;
        stk.push(curr);
        
        int num = curr == size - 1? 1 : udp[curr+1] + 1;
        udp[curr] = num;
        while(curr > 0 && udp[curr-1] != 0) {
            udp[--curr] += num;
        }
        
        curr = idx;
        num = curr == 0? 1 : ddp[curr-1] + 1;
        ddp[curr] = num;
        while(curr < size - 1 && ddp[curr+1] != 0) {
            ddp[++curr] += num;
        }
    }
    
    private void rollback() {
        int idx = stk.pop();
        isDeleted[idx] = false;
        
        int num = udp[idx];
        int curr = idx;
        while(curr > 0 && udp[curr-1] != 0) {
            udp[--curr] -= num;
        }
        udp[idx] = 0;
        
        num = ddp[idx];
        curr = idx;
        while(curr < size-1 && ddp[curr+1] != 0) {
            ddp[++curr] -= num;
        }
        ddp[idx] = 0;
        
    }
}
