import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);

        for(int test_case = 1; test_case <= 1; test_case++)
        {
            int n = sc.nextInt();
            //String str = "";
            for (int i = 1; i <= n; i++) {
                String numStr = String.valueOf(i);
                numStr = numStr.replaceAll("[369]", "-");
                numStr = numStr.contains("-") ? numStr.replaceAll("[0-9]", "") : numStr; 
                System.out.print(numStr + " ");
            }
        }
    }
}
