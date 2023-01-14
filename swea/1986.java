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
            int sum = 1;
            int operand = -1;
             
            for (int i = 2;i <= n; i++) {
                sum += (operand * i);
                operand *= -1;
            }
             
            System.out.println("#" + test_case + " " + sum);
 
        }
    }
}
