import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main {
    static class Position {
        int r;		// 행
        int c;		// 열
        int u;		// 가지고있는 우산 내구도
        int life;	// 현재 목숨
 
        public Position(int r, int c, int u, int life) {
            this.r = r;
            this.c = c;
            this.u = u;
            this.life = life;
        }
    }
 
    static Position player;
    static int N, H, D, route = -1;
    static char[][] matrix;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    static int[][] isVisited;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 한 변 길이
        H = Integer.parseInt(st.nextToken());   // 현재 체력
        D = Integer.parseInt(st.nextToken());   // 우산 내구도
 
        matrix = new char[N][N];
        for (int r = 0; r < N; r++) {
            matrix[r] = br.readLine().toCharArray();
            for (int c = 0; c < N; c++) {
                if (matrix[r][c] == 'S')
                    player = new Position(r, c, 0, H);
            }
        }
 
        isVisited = new int[N][N];
        bfs();
 
        System.out.println(route);
    }
 
    private static void bfs() {
        Queue<Position> queue = new LinkedList<>();
        isVisited[player.r][player.c] = player.life;
        queue.offer(player);
 
        int cnt = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (--size >= 0) {
                Position p = queue.poll();
 
                for (int i = 0; i < 4; i++) {
                    int nr = p.r + dr[i];
                    int nc = p.c + dc[i];
 
                    if (nr < 0 || nc < 0 || nr >= N || nc >= N)
                        continue;
 
                    if (matrix[nr][nc] == 'E') {
                        route = cnt + 1;
                        return;
                    }
 
                    int nu = p.u, nlife = p.life;
                    if (matrix[nr][nc] == 'U')
                        nu = D;
 
                    if (nu != 0)
                        nu--;   // 우산 내구도 감소
                    else
                        nlife--;    // 목숨 감소
 
                    if (nlife == 0)
                        continue;
 
                    if (isVisited[nr][nc] < nlife + nu) {
                        isVisited[nr][nc] = nlife + nu;
                        queue.offer(new Position(nr, nc, nu, nlife));
                    }
                }
            }
            cnt++;
        }
    }
}
