import java.util.*;

class Solution {
    Queue<Integer> q = new LinkedList<>();
    int[] binNum;
    int size;
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            long num = numbers[i];
            binNum = getBinNum(num);
            if (binNum[size/2] == 0) {
                answer[i] = 0;
                continue;
            } 
            answer[i] = check(size/2, size/2, true)? 1 : 0;
        }
        
        return answer;
    }
    
    private boolean check(int currIdx, int currSize, boolean hasParent) {
        if (!hasParent && binNum[currIdx] == 1) return false;
        if (currSize < 1) {
            return true;
        }
        
        int half = currSize / 2;
        return check(currIdx - (half + 1), half, hasParent && binNum[currIdx - (half + 1)] == 1) 
            && check(currIdx + (half + 1), half, hasParent && binNum[currIdx + (half + 1)] == 1);
    }
    
    private int[] getBinNum(long num) {        
        long curr = num;
        while(curr > 0) {
            q.add((int)(curr%2));
            curr /= 2;
        }
        
        size = getLength(q.size());
        int[] binNum = new int[size];
        int currIdx = 0;
        while (!q.isEmpty()) {
            binNum[currIdx++] = q.poll();
        }
        
        return binNum;
    }
    
    private int getLength(long cnt) {
        int h = 1;
        while (Math.pow(2, h) <= cnt) {
            h++;
        }
        
        return (int) Math.pow(2, h) - 1;
    }
    
}
