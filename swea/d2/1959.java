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
            int m = sc.nextInt();
             
            sc.nextLine();
             
            String first = sc.nextLine();
            String second = sc.nextLine();
             
            int[] firstArr = getArr(first);
            int[] secondArr = getArr(second);
             
            int result = n < m? getMax(firstArr, secondArr) : getMax(secondArr, firstArr);
            System.out.println("#" + test_case + " " + result);
        }
    }
     
    private static int[] getArr(String str) {
        String[] strs = str.split(" ");
        int[] arr = new int[strs.length];
         
        for (int i = 0; i < strs.length; i++) {
            arr[i] = Integer.parseInt(strs[i]);
        }
         
        return arr;
    }
     
    private static int getMax(int[] shortArr, int[] longArr) {
        int max = -1;
        int s = shortArr.length;
        int l = longArr.length;
         
        for (int i = 0; i<= l-s; i++) {
            int currentSum = 0;
            for (int j = 0; j < s; j++) {
                currentSum += (shortArr[j] * longArr[i+j]);
            }
            max = Math.max(max, currentSum);
        }
         
        return max;
    }
}
