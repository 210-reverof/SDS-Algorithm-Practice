import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int[][] points = new int[board.length + 1][board[0].length + 1];
        int[][] sums = new int[board.length][board[0].length];
        int H = board.length, W = board[0].length;
        int answer = 0;
        
        for (int[] s : skill) {
            int e = s[0] == 1? -1 * s[5] : s[5];
            points[s[1]][s[2]] += e;
            points[s[1]][s[4]+1] += -1 * e;
            points[s[3]+1][s[2]] += -1 * e;
            points[s[3]+1][s[4]+1] += e;
        }
        
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                int leftTop = i > 0 && j > 0? sums[i-1][j-1] : 0;
                int top = i > 0? sums[i-1][j] : 0;
                int left = j > 0? sums[i][j-1] : 0;
                
                sums[i][j] = top + left - leftTop + points[i][j];
                if (sums[i][j] + board[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}
