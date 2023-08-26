import java.util.*;

class Solution {
    Set<Integer> visited = new HashSet<>();
    public int solution(String dirs) {
        int r = 0, c = 0;
        for (char d : dirs.toCharArray()) {
            int nr = r + getNextR(d);
            int nc = c + getNextC(d);
            
            if (nr < -5 || nr > 5 || nc < -5 || nc > 5) continue;
            
            visited.add(getIndex(r, c, nr, nc));
            r = nr;
            c = nc;
        }
        
        System.out.println(visited);
        return visited.size();
    }
    
    private int getNextR(char c) {
        if (c == 'U') return 1;
        if (c == 'D') return -1;
        if (c == 'R') return 0;
        return 0;
    }
    
    private int getNextC(char c) {
        if (c == 'U') return 0;
        if (c == 'D') return 0;
        if (c == 'R') return 1;
        return -1;
    }
    
    private int getIndex(int r, int c, int nr, int nc) {
        int lr = (nr + r + 10) * 2;
        int lc = (nc + c + 10) * 2;
        int res = lr * 100 + lc;
        return res;
    }
}
