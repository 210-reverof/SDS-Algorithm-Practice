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
            int aM= sc.nextInt();
            int aD = sc.nextInt();
            int bM = sc.nextInt();
            int bD = sc.nextInt();
                
            int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
            
            for (int i = 1; i <= 12;i ++) {
            	days[i] = days[i] + days[i-1];
            }
            
            int a = days[aM-1] + aD;
            int b = days[bM-1] + bD;
            
            System.out.println("#" + test_case + " " + (b-a+1));
            

		}
	}
}
