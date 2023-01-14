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
            char[] chars = sc.next().toCharArray();
            int length = chars.length;
             
            int isSame = 1;
            for (int i = 0; i < length / 2; i++) {
                if (chars[i] != chars[length-1-i]) {
                    isSame = 0;
                    break;
                }
            }
             
            System.out.println("#" + test_case + " " + isSame);
              
        }
    }
}
