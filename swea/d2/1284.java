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
            int p = sc.nextInt();
            int q = sc.nextInt();
            int r = sc.nextInt();
            int s = sc.nextInt();
            int w = sc.nextInt();
            
            int a = p * w; 
            int b = w <= r ? q : q + (w-r) * s;
            int result = a < b ? a : b;
            
            System.out.println("#" + test_case + " " + result);

		}
	}
}
