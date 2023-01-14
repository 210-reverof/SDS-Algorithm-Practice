import java.util.*;
import java.io.FileInputStream;

class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int cnt = sc.nextInt();
            int oddCnt = 0;
            int evenCnt = 0;
            int result = 0;
            int max = -1;
            List<Integer> nums = new ArrayList<>();

            for (int i = 0; i < cnt; i++) {
                int num = sc.nextInt();
                max = Math.max(max, num);
                nums.add(num);
            }

            for (Integer i : nums) {
                int num = max - i;
                if (num % 2 == 1) {
                    oddCnt++;
                }
                evenCnt += num / 2;
            }

            if (evenCnt < oddCnt) {
                result = oddCnt * 2 - 1;
            } else if (evenCnt == oddCnt) {
                result = oddCnt * 2;
            } else {
                int moreEvenCnt = evenCnt - oddCnt;
                result = oddCnt * 2;

                // 남은 짝수 처리하기
                result += getNum(moreEvenCnt);
            }

            System.out.println("#" + test_case + " " + result);
        }
    }

    private static int getNum(int n) {
        int result = n % 3;
        result = result == 0 ? 3 : result;
        result += ((n - 1) / 3) * 4 + 1;

        return result;
    }
}
