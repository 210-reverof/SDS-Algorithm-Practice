import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        Queue<Integer> q = new PriorityQueue<>();
        
        for (String str: timetable) {
            String[] strs = str.split(":");
            int hour = Integer.parseInt(strs[0]);
            int minute = Integer.parseInt(strs[1]);
            q.add(hour*60 + minute);
        }
        
        int currTime = 540;
        int answer = 540 + t*(n-1);
        for (int i = 0; i < n; i++) {
            int cnt = 0; 
            
            while(!q.isEmpty() && cnt < m) {
                if (q.peek() > currTime) break;
                
                int time = q.poll();
                cnt++;
                
                if (i == n-1 && cnt == m) {
                    answer = time - 1;
                    break;
                }
            }
            
            currTime += t;
        }
        
        return toTimeStr(answer/60) + ":" + toTimeStr(answer%60);
    }
    
    private String toTimeStr(int num) {
        return num < 10? "0" + num : String.valueOf(num); 
    }
}
