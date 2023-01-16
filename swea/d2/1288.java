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
            int N = sc.nextInt();
            HashSet<Character> set = new HashSet<>();
             
            int i = 1;
            while(true) {
                int num = N * i;
                char[] numChars = String.valueOf(num).toCharArray();
                 
                for (char c : numChars) {
                    set.add(c);
                }
                if (set.size() == 10) break;
                i++;
            }
             
            System.out.println("#" + test_case + " " + (N*i));
        }
    }
}
