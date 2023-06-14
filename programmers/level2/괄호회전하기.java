import java.util.*;

class Solution {
    int len;
    char[] ss;
    Deque<Character> stack = new ArrayDeque<>();
    public int solution(String s) {
        int answer = 0;
        ss = s.toCharArray();
        len = s.length();
        
        for (int i = 0; i < len; i++)
            if (isRight(i)) answer++;
        
        return answer;
    }
    
    private boolean isRight(int startIdx) {
        for (int i = 0; i < len; i++) {
            char c = ss[(i + startIdx) % len];
            
            if (c == '[' || c == '{' || c == '(') {
                stack.push(c);
                continue;
            }
            
            if (stack.isEmpty()) return false;
            if (stack.peek() == getOpp(c)) stack.pop();
            else return false;   
        }
        
        if (stack.size() == 0) return true;
        return false;
    }
    
    private char getOpp(char c) {
        if (c == ']') return '[';
        if (c == '}') return '{';
        return '(';
    }
}
