import java.util.*;

class Solution {
    int[][] ds = {{0,1}, {1,0}, {-1,0}, {0,-1}};
    Map<Integer, Integer> volumes = new HashMap<>();
    int H, W;
    Queue<int[]> q = new LinkedList<>();
    int[][] map;
    public int solution(int[][] land) {
        int answer = 0;
        H = land.length;
        W = land[0].length;
        map = land;
        
        int label = 2;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] != 1) continue;
                int size = bfs(i, j, label);
                volumes.put(label++, size);
            }
        }
        
        Set<Integer> set = new HashSet<>();
        for (int j = 0; j < W; j++) {
            for (int i = 0; i < H; i++) {
                if (map[i][j] == 0) continue;
                set.add(map[i][j]);
            }
            
            int currCnt = 0;
            for (Integer l : set) {
                currCnt += volumes.get(l);
            }
            set.clear();
            
            answer = Math.max(answer, currCnt);
        }
        
        return answer;
    }
    
    private int bfs(int startR, int startC, int label) {
        int size = 1;
        map[startR][startC] = label;
        q.add(new int[]{ startR, startC });
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = curr[0] + ds[d][0];
                int nc = curr[1] + ds[d][1];
                
                if (nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] != 1) continue;
                map[nr][nc] = label;
                size++;
                q.add(new int[]{nr, nc});
            }
        }
        
        return size;
    }
}
