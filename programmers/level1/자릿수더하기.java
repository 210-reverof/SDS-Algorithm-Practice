import java.util.*;

public class Solution {
    public int solution(int n) {
        int answer = 0;
        char[] digits = String.valueOf(n).toCharArray();
        
        for (char c : digits) {
            answer += c - '0';
        }

        return answer;
    }
}
