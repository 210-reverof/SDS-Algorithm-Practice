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
            int aH = sc.nextInt();
            int aM = sc.nextInt();
            int bH = sc.nextInt();
            int bM = sc.nextInt();
             
            int resultM = (aM + bM) % 60;
            int resultH = (((aM + bM) / 60) + aH + bH) % 12;
            resultH = resultH == 0 ? 12 : resultH;
             
           System.out.println("#" + test_case + " " + resultH + " " + resultM);
        }
    }
}
