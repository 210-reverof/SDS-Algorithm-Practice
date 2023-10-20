import java.util.*;

class Solution {
    int[] a, b;
    boolean[] used;
    int size;
    int answer = 0;
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        size = A.length;
        boolean[] used = new boolean[size];
        
        for (int i = 0; i < size; i++) {
             for (int j = i; j < size; j++) {
                if (used[j] || B[j] <= A[i]) continue;
                used[j] = true;
                answer++;
                break;
            }
        }
        
        return answer;
    }
}
