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
            int cnt = sc.nextInt();
            int curser = 0;
 
            System.out.println("#" + test_case);
            for (int i = 0;i < cnt; i++) {
                String str = sc.next();
                int num = sc.nextInt();
                 
                for (int j = 0; j < num; j++) {
                    System.out.print(str);
                    curser++;
                    if (curser == 10) {
                        System.out.println("");
                        curser = 0;
                    }
                }
            }
             
            System.out.println("");
        }
    }
}
