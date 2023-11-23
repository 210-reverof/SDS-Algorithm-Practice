import java.util.*;

class Solution {
    int[][] b;
    int answer;
    public int solution(int[][] board, int[][] skill) {
        b = board;
        answer = board.length * board[0].length;
        
        for (int[] s : skill) {
            if (s[0] == 1) affect(s[1], s[2], s[3], s[4], -1 * s[5]);
            else affect(s[1], s[2], s[3], s[4], s[5]);
        }
        
        return answer;
    }
    
    private void affect(int r1, int c1, int r2, int c2, int degree) {
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                if (b[i][j] > 0 && b[i][j] + degree <= 0) answer--;
                else if (b[i][j] <= 0 && b[i][j] + degree > 0) answer++;
                b[i][j] += degree;
            }
        }
    }
}
