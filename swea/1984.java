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
            int[] nums = new int[10];
            
            for (int i = 0; i < 10; i++) {
                nums[i] = sc.nextInt();
            }
            
            Arrays.sort(nums);
            
            int sum = 0;
            for (int i = 1; i < 9; i++) {
            	sum += nums[i];
            }
            
            double avg = Math.round(sum / 8.0);
            
            System.out.println("#" + test_case + " " + (int)avg);

		}
	}
}
