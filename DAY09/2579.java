import java.util.*;

public class Main {
	static int n;
	
	static int dp[], cost[];
	
	
	public static void main(String[] args)   {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n= Integer.parseInt(br.readline());
		cost = new int[n+1];
		dp = new int[n+1]; 
		
		
		for(int i=1;i<=n;i++) {
			cost[i]=sc.nextInt();
		}
		
		dp[1] = cost[1]; 
		dp[2] = Math.max(dp[1] + cost[2], cost[2]);
		
		for(int i=3;i<=n;i++) {
			dp[i] = Math.max(dp[i-2] + cost[i] , cost[i-1] + dp[i-3] + cost[i]);
			
		}
		
		System.out.println(dp[n]);
		
	}
	
}
