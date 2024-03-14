import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        int[] cnts = new int[10001];
        
        for (int w : weights) {
            cnts[w]++;
            if (w*2 % 3 == 0) cnts[w*2/3]++;
            if (w % 2 == 0) cnts[w/2]++;
            if (w*3 % 2 == 0) cnts[w*3/2]++;
            if (w*3 % 4 == 0) cnts[w*3/4]++;
            if (w*4 % 3 == 0) cnts[w*4/3]++;
            cnts[w*2]++;
        }
        
        for (int w : weights) {
            answer += cnts[w] - 1;
            
            if (w*2 % 3 == 0) {
                answer += cnts[w*2/3] -1;
            }
            
            if (w % 2 == 0) { 
                answer += cnts[w/2] - 1;
            }
            
            if (w*3 % 2 == 0) { 
                answer += cnts[w*3/2] - 1;
            }
            
            if (w*3 % 4 == 0) { 
                answer += cnts[w*3/4] - 1;
            }
            
            if (w*4 % 3 == 0)  {
                answer += cnts[w*4/3] - 1;
            }
            
            answer += cnts[w*2] - 1;
        }
        
        return answer / 2;  
    }
}
