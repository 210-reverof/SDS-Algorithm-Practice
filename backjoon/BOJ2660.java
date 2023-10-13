import java.util.*;
import java.io.*;

public class Main {
	static public void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[][] dist = new int[n+1][n+1];
		int INF = 10000000;
		
		for (int i = 1; i <= n; i++) Arrays.fill(dist[i], INF);
		while (true) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			if (start == -1) break;
			int end = Integer.parseInt(st.nextToken());
			
			dist[start][end] = 1;
			dist[end][start] = 1;
		}
		
		for(int k = 1; k <= n; k++){
		    for(int i = 1; i <= n; i++){
		    	if (i == k) continue;
		        for(int j = 1; j <=n ; j++){
		        	if (j == i || j == k) continue;
		            dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
		        }
		    }
		}
		
		int min = INF;
		Queue<Integer> answers = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			int currMax = -1;
			for (int j = 1; j <= n; j++) {
				if (i == j) continue;
				currMax = Math.max(currMax, dist[i][j]);
				if (currMax > min) break;
			}
			
			if (currMax == min) answers.add(i);
			if (currMax < min) {
				min = currMax;
				answers.clear();
				answers.add(i);
			}
		}
		
		StringBuilder sb = new StringBuilder(min + " " + answers.size() + "\n");
		sb.append(answers.poll());
		while(!answers.isEmpty()) {
			sb.append(" " + answers.poll());
		}
		
		System.out.println(sb);
		
	}
}
