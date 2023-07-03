import java.util.*;

class Solution {
    
    int[] answer = {1, 0};
    public int[] solution(String s) {
        
        while(true) {
            int prevLength = s.length();
            s = s.replaceAll("0", "");
            int currLength = s.length();
            answer[1] += prevLength - currLength;
            
            if (currLength == 1) break;
            
            answer[0]++;
            s = getNext(currLength);
        }
        
        return answer;
    }
    
    private String getNext(int num) {
        String result = "";
        
        while (num > 0) {
            result = (num % 2) + result;
            num /= 2;
        }
        
        return result;
    }
}
