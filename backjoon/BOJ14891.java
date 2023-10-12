import java.util.*;
import java.io.*;

public class Main {
	
	static char[][] gears = new char[4][8];
	static int[] topIdxs = {0, 0, 0, 0};
	static public void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Queue<int[]> q = new LinkedList<>(); 

		for (int i = 0; i < 4; i++) {
			gears[i] = br.readLine().toCharArray();
		}
		
		int K = Integer.parseInt(br.readLine());
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int currGearIdx = Integer.parseInt(st.nextToken()) - 1;
			int direction = Integer.parseInt(st.nextToken());
			q.add(new int[] {currGearIdx, direction});
			
			char currState = gears[currGearIdx][(topIdxs[currGearIdx] + 6) % 8];
			int nextDir = -1 * direction;
			for (int i = currGearIdx - 1; i >= 0; i--) {
				if (gears[i][(topIdxs[i] + 2) % 8] != currState) {
					q.add(new int[] {i, nextDir});
					nextDir *= -1;
					currState = gears[i][(topIdxs[i] + 6) % 8];
					continue;
				}
				break;
			}
			
			currState = gears[currGearIdx][(topIdxs[currGearIdx] + 2) % 8];
			nextDir = -1 * direction;
			for (int i = currGearIdx + 1; i < 4; i++) {
				if (gears[i][(topIdxs[i] + 6) % 8] != currState) {
					q.add(new int[] {i, nextDir});
					nextDir *= -1;
					currState = gears[i][(topIdxs[i] + 2) % 8];
					continue;
				}
				break;
			}
			
			while(!q.isEmpty()) {
				int[] t = q.poll();
				if (t[1] == 1) turn(t[0]);
				else turnOpp(t[0]);
			}
			
		}
		
		int answer = 0;
		for (int i = 0; i < 4; i++) {
			if (gears[i][topIdxs[i]] == '1') {
				answer += Math.pow(2, i); 
			}
		}
		
		System.out.println(answer);
		
	}
	
	static private void turnOpp(int gearIdx) {
		if (topIdxs[gearIdx] == 7) topIdxs[gearIdx] = 0;
		else topIdxs[gearIdx]++;
	}
	
	static private void turn(int gearIdx) {
		if (topIdxs[gearIdx] == 0) topIdxs[gearIdx] = 7;
		else topIdxs[gearIdx]--;
	}
}
