import java.util.*;
import java.io.FileInputStream;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
 
        for(int test_case = 1; test_case <= 10; test_case++)
        {
            int dumpCnt = sc.nextInt();
            List<Integer> nums = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                int num = sc.nextInt();
                nums.add(num);
            }
             
            for (int i = 0; i < dumpCnt; i++) {
                Collections.sort(nums);
                nums.set(0, nums.get(0) + 1);
                nums.set(99, nums.get(99) - 1);
                //nums[99]--;
            }
             
            Collections.sort(nums);
            System.out.println("#" + test_case + " " + (nums.get(99) - nums.get(0)));
        }
    }
}
