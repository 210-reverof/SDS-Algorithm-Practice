import java.util.Scanner;
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
            int n = sc.nextInt();
            int dis = 0;
            int speed = 0;
            for (int i = 0; i < n; i++) {
                int cmd = sc.nextInt();
                 
                if (cmd == 0) {
                    dis += speed;
                    continue;
                } else if ( cmd == 1) {
                    int d = sc.nextInt();
                    speed += d; 
                    dis += speed;
                } else {
                    int d = sc.nextInt();
                    speed -= d;
                    speed = speed < 0 ? 0 : speed;
                    dis += speed;
                }
            }
            System.out.println("#" + test_case + " " + dis);
 
        }
    }
}
