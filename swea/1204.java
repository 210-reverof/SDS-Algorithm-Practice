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
            int caseNum = sc.nextInt();
            HashMap<Integer, Integer> map = new HashMap<>();    // <숫자, 빈도수>
             
            for (int i = 0; i < 1000; i++) {
                int num = sc.nextInt();
                map.put(num, map.getOrDefault(num,0) + 1);
            }
             
            int maxCnt = -1;
            int maxNum = -1;
             
            for (Integer num : map.keySet()) {
                int currCnt = map.get(num);
                if (currCnt > maxCnt || (currCnt == maxCnt && num > maxNum) ) {
                    maxCnt = currCnt;
                    maxNum = num;
                }
            }
             
            System.out.println("#" + caseNum + " " + maxNum);
 
        }
    }
}
