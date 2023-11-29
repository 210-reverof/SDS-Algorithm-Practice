import java.util.*;

class Solution {
    int[][] board;
    public int[] solution(int[][] arr) {
        board = arr;
        int[] answer = find(0, 0, arr.length);
        
        return new int[]{answer[0], answer[1]};
    }
    
    // { 0개수, 1개수, allSame }
    private int[] find(int startR, int startC, int n) {
        if (n == 1) {
            return board[startR][startC] == 0? new int[]{1, 0, 1} : new int[]{0, 1, 1};
        }
        
        int[] a = find(startR, startC, n/2);
        int[] b = find(startR, startC + n/2, n/2);
        int[] c = find(startR + n/2, startC, n/2);
        int[] d = find(startR + n/2, startC + n/2, n/2);
        
        int cnt0 = a[0] + b[0] + c[0] + d[0];
        int cnt1 = a[1] + b[1] + c[1] + d[1];
        
        if (cnt0 == 0) {
            cnt1 = 1;
            return new int[]{cnt0, cnt1, 1};   
        }
        
        if (cnt1 == 0) {
            cnt0 = 1;
            return new int[]{cnt0, cnt1, 1};
        }
        
        return new int[]{cnt0, cnt1, 0}; 
    }
}
