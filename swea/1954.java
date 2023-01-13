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
            System.out.println("#"+ test_case);
            int[][] nums = new int[n][n];
             
            fill(nums, n, 0, 1);
            print(nums, n);
        }
    }
     
    private static void fill(int[][] nums, int n, int depth, int startNum) {
        if (n % 2 == 0 && depth == n / 2) {
            return;
        } else if (depth == n / 2) {
            nums[depth][depth] = startNum;
            return;
        }
         
        int currentNum = startNum;
        for (int i = depth; i < n-depth; i++)
            nums[depth][i] = currentNum++;
         
        for (int i = depth + 1; i < n-depth-1; i++)
            nums[i][n-depth-1] = currentNum++;
         
        for (int i = n-depth-1; i >= depth; i--)
            nums[n-depth-1][i] = currentNum++;
         
        for (int i = n-depth-2; i > depth; i--)
            nums[i][depth] = currentNum++;  
        
        fill(nums, n, depth+1, currentNum);
    }
     
    private static void print(int[][] nums, int n) {
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(nums[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
