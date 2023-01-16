import java.util.*;
import java.io.FileInputStream;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
 
        while (sc.hasNext())
        {
            String caseNum = sc.nextLine();
             
            int rightDownNum = 0;
            int leftDownNum = 0;
            int[] verticalSums = new int[100];
             
            Queue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
             
            for (int i = 0; i<100;i++) {
                String currentLine = sc.nextLine();
                String[] nums = currentLine.split(" "); 
                int horizonSum = 0;
                for (int j = 0;j<100;j++) {
                    int num = Integer.parseInt(nums[j]);
                    horizonSum += num;
                     
                    if (i == j) rightDownNum += num;
                    if (i == 99 - j) leftDownNum += num;
                    verticalSums[j] += num;
                }
                q.add(horizonSum);
            }
            q.add(rightDownNum);
            q.add(leftDownNum);
             
            for (int i = 0; i<99;i++) {
                q.add(verticalSums[i]);
            }
            System.out.println("#" + caseNum + " " + q.peek());
        }
    }
}
