import java.util.Scanner;
import java.io.FileInputStream;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
 
        for(int test_case = 1; test_case <= 10; test_case++)
        {
            int cnt = sc.nextInt();
            int[] map = new int[cnt];
            for (int i = 0; i < cnt; i++) {
                int n = sc.nextInt();
                map[i] = n;
            }
             
            int prev1 = map[0];
            int prev2 = map[1];
            int next1 = map[3];
            int next2 = map[4];
            int result = 0;
             
            for (int i = 2; i < cnt - 2; i++) {
                prev1 = map[i-2];
                prev2 = map[i-1];
                next1 = map[i+1];
                next2 = map[i+2];
                 
                int max = Math.max( Math.max(prev1, prev2) , Math.max(next1, next2) );
                int dis = map[i] - max;
                dis = dis < 0 ? 0 : dis;
                result += dis; 
            }
             
            System.out.println("#" + test_case + " " + result);
        }
    }
}
