import java.util.*;

class Solution {
    int[] pick, used, ms;
    int[][] energy;
    int len, pickCnt, answer = Integer.MAX_VALUE;
    public int solution(int[] picks, String[] minerals) {
        energy = new int[][]{{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
        pick = picks;
        used = new int[3];
        len = minerals.length;
        ms = new int[len];
        
        for (int p : picks) {
            pickCnt += p;
        }
        
        for (int i = 0; i < len; i++) {
            String min = minerals[i];
            ms[i] = min.equals("diamond") ? 0 : min.equals("iron") ? 1 : 2; 
        }
        
        find(0, 0, 0, 0);
        
        return answer;
    }
    
    private void find(int cnt, int p, int usedEnergy, int depth) {
        if (usedEnergy >= answer) return;
        if (depth < len && cnt > 0) {
            find(cnt-1, p, usedEnergy + energy[p][ms[depth]] , depth+1);
            return;
        }
        
        if (depth == len || used[0] + used[1] + used[2] == pickCnt) {
            answer = Math.min(answer, usedEnergy);
            return;
        }
        
        for (int i = 0; i < 3; i++) {
            if (used[i] >= pick[i]) continue;
            used[i]++;
            find(4, i, usedEnergy + energy[i][ms[depth]], depth+1);
            used[i]--;
        }
    }
}
