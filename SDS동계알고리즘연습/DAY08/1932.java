import java.util.*;
import java.io.*;


// 1932

class Main {
	static int n;
	static int dp[][];
	static int nums[][];
    static public void main (String [] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //StringTokenizer st = new StringTokenizer(br.readLine(), " ", false);
        
        n = Integer.parseInt(br.readLine());
        nums = new int[n][n];
        dp = new int[n][n];
        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine(), " ", false);
        	
        	for (int j = 0; j <= i; j++) {
        		nums[i][j] = Integer.parseInt(st.nextToken());
        		
        		if (i == 0) {
        			dp[i][j] = nums[i][j];
        		}
        		else {
        			int bigger = 0;
        			if (j-1 < 0) bigger = dp[i-1][j];
        			else bigger = Math.max(dp[i-1][j], dp[i-1][j-1]); 
        			dp[i][j] = nums[i][j] + bigger;

        		}
        	
        	}
        }
        
        int max = -1;
        for (int i = 0; i<n;i++)
        	max = max > dp[n-1][i]? max : dp[n-1][i];
        
        System.out.println(max + "");
    }
}
