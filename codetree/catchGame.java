import java.util.*;
import java.io.*;

public class Main {
    static int N, M, H, K, catcherR, catcherC, catcherTurn = 0, answer = 0;
    static int[][] ds = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int[][] road, runners;
    static Queue<int[]> nexts = new LinkedList<>();
    static Set<Integer> trees = new HashSet<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        catcherC = catcherR = N/2;
        road = new int[N][N];
        runners = new int[N][N];
        fillDirections();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int type = Integer.parseInt(st.nextToken());
            runners[r][c] = type;
        }

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            trees.add(r*N+c);
        }

        // print();
        for (int k = 1; k <= K; k++) {
            run();
            move();
            check(k);

            // print();
        }

        System.out.println(answer);
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (trees.contains(i*N+j)) System.out.print("9 ");
                else System.out.print(runners[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void run() {
        // 술래랑 거리 3 이내에
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; i+j <= 3; j++) {
                findRunner(catcherR + i, catcherC + j);
                findRunner(catcherR + i, catcherC - j);
                findRunner(catcherR - i, catcherC + j);
                findRunner(catcherR - i, catcherC - j);                                 
            }
        }

        while(!nexts.isEmpty()) {
            int[] curr = nexts.poll();
            // System.out.println("q------ " + Arrays.toString(curr));
            runners[curr[1]][curr[2]] = curr[0];
        }
    }

    private static void findRunner(int r, int c) {
        if (r < 0 || r >= N || c < 0 || c >= N || runners[r][c] == 0) return;
                
        int d = runners[r][c];
        int nr = r + ds[d][0];
        int nc = c + ds[d][1];
        if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
            d = runners[r][c] = (d+2) % 4;
            nr = r + ds[d][0];
            nc = c + ds[d][1];
        }
        if (nr == catcherR && nc == catcherC) return;
        
        nexts.add(new int[]{runners[r][c], nr, nc});
        runners[r][c] = 0;  
    }

    private static void move() {
        if (catcherR == 0 && catcherC == 0 && catcherTurn == 0) catcherTurn = 2;
        else if (catcherR == N/2 && catcherC == N/2 && catcherTurn == 2) catcherTurn = 0;
        int nd = (road[catcherR][catcherC] + catcherTurn) % 4;
        catcherR = (catcherR + ds[nd][0]) % 4;
        catcherC = (catcherC + ds[nd][1]) % 4;
    }

    private static void check(int k) {
        int d = (road[catcherR][catcherC] + catcherTurn) % 4;

        for (int i = 0; i < 3; i++) {
            int nr = catcherR + (i * ds[d][0]);
            int nc = catcherC + (i * ds[d][1]);
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) break;
            if (runners[nr][nc] != 0 && !trees.contains(nr*N+nc)) {
                answer += k;
                runners[nr][nc] = 0;
            }
        }
    }

    private static void fillDirections() {
        int r = N/2;
        int c = N/2;
        int currSize = 1;

        road[r][c] = 0;
        while(currSize < N) {
            currSize += 2;
            r--;
            road[r][c-1] = 0;
            for (int i = 0; i < currSize-1; i++) {
                if (i < currSize-2) road[r][c+i] = 1;
                road[r+i][c+currSize-2] = 2;
                road[r+currSize-1][c+currSize-2-i] = 3;
                road[r+currSize-1-i][c-1] = 0;
            }
            c--;
        }
    }
}
