import java.io.*;
import java.util.*;

// DFS는 동시에 하나 씩 이동하는 것을 처리할 수 없음
// BFS는 DFS로 해결한 문제를 모두 풀 수 있지만 DFS는 아니다.

// 고슴도치 이동에 대한 영역
// 1. 큐에서 꺼내옴
// 2. 목적지인가? D
// 3. 연결된 곳을 순회 : 상하좌우 4가지 방향
// 4.   갈 수 있는가? (1. 맵 영역 안, 2. .이고, 물이 인접하지 않는 곳[큐에 물을 먼저 넣으면 물이 인접한지 안한지 체크 안해도 됨], 3. 방문하지 않은 곳)
// 5.       체크인 - 다른 배열 하나 더 만들기
// 6.       큐에 넣음

// 물 이동
// 3. 상하좌우 4가지 방향
// 4.   갈 수 있는가? (1. 맵 영역, 2. .인 곳)
// 5.       체크인 - 본 배열에 표시

class Point{
	int i, j; // y가 R(행) / x가 C(열)
	public Point(int i, int j) {
		this.i = i;
		this.j = j;
	}
}

class Main {
    static char[][] arr;     // 오브제 배열
    static char[][] visit;         // 방문 여부 배열
    static Queue<Point> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ", false);

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        visit = new char[R][C];

        Point start = null;
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = str.charAt(j);

                if (str.charAt(j) == 'S') {
                    visit[i][j] = '1';
                    start = new Point(i, j);
                } else {
                    visit[i][j] = '0';
                    if (str.charAt(j) == '*') q.add(new Point(i, j));
                }
            }
        }
        q.add(start);

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (arr[p.i][p.j] == '*') {
                // 상하좌우에 범람
                if (p.i>0 && arr[p.i-1][p.j] != 'X') {
                    arr[p.i-1][p.j] = '*';
                    q.add(new Point(p.i-1, p.j));
                }
                if (p.i<R && arr[p.i+1][p.j] != 'X') {
                    arr[p.i+1][p.j] = '*';
                    q.add(new Point(p.i+1, p.j));
                }
                if (p.j>0 && arr[p.i][p.j-1] != 'X') {
                    arr[p.i-1][p.j] = '*';
                    q.add(new Point(p.i, p.j-1));
                }
                if (p.j<C && arr[p.i][p.j+1] != 'X') {
                    arr[p.i-1][p.j] = '*';
                    q.add(new Point(p.i, p.j+1));
                }
            } else {
                // 고슴도치 이동
                if (p.i>0 && arr[p.i-1][p.j] != 'X') {
                    arr[p.i-1][p.j] = '*';
                    q.add(new Point(p.i-1, p.j));
                }
                if (p.i<R && arr[p.i+1][p.j] != 'X') {
                    arr[p.i+1][p.j] = '*';
                    q.add(new Point(p.i+1, p.j));
                }
                if (p.j>0 && arr[p.i][p.j-1] != 'X') {
                    arr[p.i-1][p.j] = '*';
                    q.add(new Point(p.i, p.j-1));
                }
                if (p.j<C && arr[p.i][p.j+1] != 'X') {
                    arr[p.i-1][p.j] = '*';
                    q.add(new Point(p.i, p.j+1));
                }
            }
        }


        System.out.println(" ");
    }
}
