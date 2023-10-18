import java.util.*;

class Solution {
    int[] a, b;
    boolean[] used;
    int size;
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(B);
        a = A; 
        b = B; 
        size = A.length;
        used = new boolean[size];
        
        for (int i = 0; i < size; i++) {
             getWinIdx(a[i]);
        }
        
        return answer;
    }
    
    private int getWinIdx(int num) {
        if (num >= b[size - 1]) return -1;
        int left = 0, right = size - 1;
        
        while (left <= right) {
            int mid = (right + left) / 2;
            if (num == b[mid]) break;
            left++;
        }
        
        return mid + 1;
    }
}
