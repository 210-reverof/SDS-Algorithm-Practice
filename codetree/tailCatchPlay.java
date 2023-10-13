import java.util.*;
import java.io.*;

public class Main {
	static int[][] ds = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
	static int[][] board;
 	static int N, M, K;
 	static Team[] teams;
 	static boolean[][] visited;
	static int shootR, shootC, shootD;
	static public void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		teams = new Team[M+1];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int currM = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1) {
					teams[currM] = makeTeam(i, j, currM++);
				}
			}
		}
		
	
		int answer = 0;
		for (int k = 0; k < K; k++) {
			setShoot(k);
			moveAllTeams();
			
			for (int i = 0; i < N; i++) {
				int nr = shootR + (ds[shootD][0] * i);
				int nc = shootC + (ds[shootD][1] * i);
				if (board[nr][nc] != 0) {
					int currTeam = board[nr][nc] / 1000;
					int currOrder = teams[currTeam].goHead? board[nr][nc] % 1000 : teams[currTeam].peopleCnt + 1 - board[nr][nc] % 1000;
					
					teams[currTeam].goHead = !teams[currTeam].goHead;
					answer += (currOrder * currOrder);
					break;
				}
			}
		}
		System.out.println(answer);
		
	}
	
	static private void setShoot(int k) {
		int currK = k % (N*4);
		if (currK < N) {
			shootD = 0;
			shootC = 0;
			shootR = currK;
			return;
		}
		
		if (currK < 2*N) {
			shootD = 1;
			shootC = currK-N;
			shootR = N-1;
			return;
		}
		
		if (currK < 3*N) {
			shootD = 2;
			shootC = N-1;
			shootR = N-1 - (currK - 2*N);
			return;
		}
		
		shootD = 3;
		shootC = N-1 - (currK-3*N);
		shootR = 0;
	}
	
	static private void moveAllTeams() {
		for (int i = 1; i <= M; i++) {
			teams[i].move();
		}
		
	}
	
	static private Team makeTeam(int startR, int startC, int m) {
		board[startR][startC] = m * 1000 + 1;
		Team team = new Team(m);
		team.route.add(startR * N + startC);
		int peopleCnt = 1;
		
		int r = startR, c = startC;
		visited[r][c] = true;
		for (int d = 0; d < 4; d++) {
			int nr = r + ds[d][0];
			int nc = c + ds[d][1];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || board[nr][nc] != 2) continue;
			visited[nr][nc] = true;
			team.route.add(nr * N + nc);
			board[nr][nc] = m * 1000 + (++peopleCnt);
			r = nr;
			c = nc;
		}
		
		while(true) {
			boolean isEnd = true;
			for (int d = 0; d < 4; d++) {
				int nr = r + ds[d][0];
				int nc = c + ds[d][1];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || board[nr][nc] == 0 || board[nr][nc] == 4) continue;
				visited[nr][nc] = true;
				team.route.add(nr * N + nc);
				board[nr][nc] = m * 1000 + (++peopleCnt);
				r = nr;
				c = nc;
				isEnd = false;
			}
			if (isEnd) break;
		}
		
		while(true) {
			boolean isEnd = true;
			for (int d = 0; d < 4; d++) {
				int nr = r + ds[d][0];
				int nc = c + ds[d][1];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || board[nr][nc] != 4) continue;
				visited[nr][nc] = true;
				team.route.add(nr * N + nc);
				board[nr][nc] = 0;
				r = nr;
				c = nc;
				isEnd = false;
			}
			if (isEnd) break;
		}
		
		team.peopleCnt = peopleCnt;
		return team;
	}
	
	static class Team {
		int num;
		int peopleCnt;
		int firstIdx;
		boolean goHead = true;
		List<Integer> route = new ArrayList<>();
		
		public Team(int num) {
			this.num = num;
		}
		
		public void move() {
			int size = route.size();
			if (goHead) {
				int currIdx = firstIdx;
				int nextIdx = currIdx == 0? size - 1 : currIdx - 1;
				int tmp = board[route.get(nextIdx)/N][route.get(nextIdx)%N];
				firstIdx = nextIdx;
				for (int i = 0; i < peopleCnt; i++) {
					if (i == size - 1) break;
					int nextR = route.get(nextIdx) / N;
					int nextC = route.get(nextIdx) % N;
					board[nextR][nextC] = board[route.get(currIdx)/N][route.get(currIdx)%N];
					nextIdx = currIdx;
					currIdx = currIdx == size - 1? 0 : currIdx + 1;
				}
				board[route.get(nextIdx)/N][route.get(nextIdx)%N] = tmp;
			}
			
			else {
				int prevIdx = firstIdx == 0? size - 1 : firstIdx - 1;
				int prevVal = board[route.get(prevIdx)/N][route.get(prevIdx)%N];
				int currIdx = firstIdx;
				firstIdx = firstIdx == size - 1? 0 : firstIdx + 1;
				for (int i = 0; i <= peopleCnt; i++) {
					int currR = route.get(currIdx) / N;
					int currC = route.get(currIdx) % N;
					int tmp = board[currR][currC];
					board[currR][currC] = prevVal;
					prevVal = tmp;
					prevIdx = currIdx;
					currIdx = currIdx == size - 1? 0 : currIdx + 1;
				}
			}
		}
	}
}
