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
            int currNum = sc.nextInt();
            int[] money = { 50000, 10000, 5000, 1000, 500, 100, 50, 10 };
             
            System.out.println("#" + test_case);
            for (int i = 0; i < 8; i++) {
                System.out.print(currNum / money[i] + " ");
                currNum %= money[i];
            }
            System.out.println("");
 
        }
    }
}
