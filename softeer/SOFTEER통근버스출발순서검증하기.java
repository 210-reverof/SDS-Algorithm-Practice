import java.util.*;
import java.io.*;


public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

        long answer = 0;
        int[] dp;
        for (int i = 0; i < N-2; i++) {
            dp = new int[N];
            int curr = nums[i];

            dp[N-1] = curr < nums[N-1] ? 0 : 1;
            for (int j = N-2; j > i; j--) {
                if (curr > nums[j]) {
                    dp[j] = dp[j+1] + 1;
                    continue;
                }

                dp[j] = dp[j+1];
                answer += dp[j];
            }
        }

        System.out.println(answer);
    }
}
