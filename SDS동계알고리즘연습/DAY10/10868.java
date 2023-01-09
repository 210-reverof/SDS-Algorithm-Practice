import java.io.*;
import java.util.*;

class Main {
	static int N, M, S;
	static int nums[];
	static int tree[];
	static int INF = Integer.MAX_VALUE;
	
	public static void main (String [] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ", false);
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new int[N+1];
		
		S = 1;
		while (S<N) S *= 2;
		tree = new int[S*2];
	
		
		for (int i = 1; i <= N;i++)
			nums[i] = Integer.parseInt(br.readLine());
		
		init(1, N, 1);
		
		for (int i = 0; i < M;i++) {
			st = new StringTokenizer(br.readLine(), " ", false);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			bw.write(findMin(1, N, 1, a, b) + "\n");
		}
		
		bw.close();
	}
	
	static int findMin(int start, int end, int node, int qLeft, int qRight ) {
		// 탈출 조건
		if (qRight < start || qLeft > end ) return INF;
		else if ( qLeft <= start && qRight >= end) return tree[node];
		else {
			int mid = ( start + end ) / 2;
			int LeftNode = findMin(start, mid, node*2, qLeft, qRight);
			int RightNode = findMin(mid+1, end, node*2+1, qLeft, qRight);
			return Math.min(LeftNode, RightNode);
		}
	}
	
	static int init(int start, int end, int node) {
		if (start == end) return tree[node] = nums[start];
		else {
			int mid = ( start + end ) / 2;
			return tree[node] = Math.min(init(start, mid, node*2), init(mid+1, end, node*2 + 1));
		}
	}
}
