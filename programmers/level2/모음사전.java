import java.util.*;

class Solution {
    public int solution(String word) {
        int answer = 0;
        double currPart = 5 + Math.pow(5, 2) + Math.pow(5, 3) + Math.pow(5, 4) + Math.pow(5, 5);
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            currPart -= Math.pow(5, 5-i);
            
            if (c == 'A') answer += 1;
            else if (c == 'E') answer += currPart + 2;
            else if (c == 'I') answer += currPart * 2 + 3;
            else if (c == 'O') answer += currPart * 3 + 4;
            else answer += currPart * 4 + 5;
        }
        
        
        return answer;
    }
    
}
