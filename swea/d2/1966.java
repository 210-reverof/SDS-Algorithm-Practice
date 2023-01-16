import java.util.*;
import java.io.FileInputStream;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
 
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int cnt = sc.nextInt();
            List<Integer> nums = new ArrayList<>();
            Queue<Integer> q = new PriorityQueue<>();
             
            for (int i = 0; i < cnt; i++) {
                int num = sc.nextInt();
                q.add(num);
            }
             
            System.out.print("#" + test_case);
            while (!q.isEmpty()) {
                System.out.print(" " + q.poll());
            }
            System.out.println("");
        }
    }
}
