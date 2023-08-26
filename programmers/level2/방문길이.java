import java.util.*;

class Solution {
    Set<Integer> visited = new HashSet<>();
    int[][] ds = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int solution(String dirs) {
        int r = 0, c = 0;
        for (char d : dirs.toCharArray()) {
            int di = d == 'D' ? 0 : d == 'U' ? 1 : d == 'R' ? 2 : 3; 
            int nr = r + ds[di][0];
            int nc = c + ds[di][1];
            
            if (nr < -5 || nr > 5 || nc < -5 || nc > 5) continue;
            
            visited.add(getIndex(r, c, nr, nc));
            r = nr;
            c = nc;
        }
        
        return visited.size();
    }
    
    private int getIndex(int r, int c, int nr, int nc) {
        int lr = (nr + r + 10) * 2;
        int lc = (nc + c + 10) * 2;
        int res = lr * 100 + lc;
        return res;
    }
}
