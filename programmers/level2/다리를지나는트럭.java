import java.util.*;

class Solution {
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i=0;i<bridge_length ; i++) q.add(0);
        int sec=0,sum=0;
        for (int t : truck_weights) {
            while(sum+t - q.peek() >weight){
                sum-=q.poll();
                q.offer(0);
                sec++;
            }
            sum = sum-q.poll()+t;
            q.offer(t);
            sec++;
        }
        return sec+bridge_length;
    }
}
