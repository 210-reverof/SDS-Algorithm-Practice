class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int target = getOneCnt(n++);
        while (getOneCnt(n) != target) n++;
        
        return n;
    }
    
    private int getOneCnt(int num) {
        int result = 0;
        
        while (num > 0) {
            if (num % 2 == 1) result++;
            num /= 2;
        }
        
        return result;
    }
}
