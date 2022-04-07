import java.io.*;
import java.util.*;

// 5626 제단
public class Main {

	static int N;
	static int[] input;
	static int[][] dp;

	static final int MOD = 1000000007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 1. 입력받기
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		dp = new int[N][(N / 2) + 2];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
			// 불가능한 경우 거르기
			if (input[i] > Math.min(i, (N - 1) - i)) {
				bw.write(String.valueOf(0));
				bw.flush();
				bw.close();
				br.close();
				return;
			}
		}

		// 2. dp 실행
		dp[0][0] = 1;
		int len;
		for (int i = 1; i < N; i++) {

			// dp배열 초기화 후 실행
			len = Math.min(i, (N-1)-i);
			for(int j=0; j<=len; j++) {
				dp[i][j] = 0;
			}
			
			// 2-1. 높이를 모르는 상태 - 0 ~ min(i, (N-1)-i) 가능
			if (input[i] == -1) {
				len = Math.min(i, (N-1)-i);
				for (int j = 0; j <= len; j++) {
					
					// 인접 열 높이 대비 +/- 1 높이만 가능
					for (int k=-1; k<=1; k++) {
						int adjHeight = j + k;
						if (adjHeight < 0) continue;
						
						dp[i][j] += dp[i-1][adjHeight];
						dp[i][j] %= MOD;
					}
				}
			}
			
			// 2-2. 높이를 아는 상태
			else {
				for (int k=-1; k<=1; k++) {
					int adjHeight = input[i] + k;
					if (adjHeight  < 0) continue;
					
					dp[i][input[i]] += dp[i-1][adjHeight];
					dp[i][input[i]] %= MOD;
				}
			}
		}

		bw.write(String.valueOf(dp[N-1][0] % MOD));

		bw.flush();
		bw.close();
		br.close();
	}
}
