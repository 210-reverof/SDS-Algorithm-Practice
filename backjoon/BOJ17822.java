import java.util.*;
import java.io.*;

public class BOJ17822 {

    static int N, M, T;
    static int[][] board;
    static int[] starts;
    static Queue<Integer> removes = new LinkedList<>();
    public static void main(String[] agrs) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        starts = new int[N];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            while(x <= N) {
                rotate(x, d, k);
                x += x;
            } 

            execute();
            print();
            System.out.println();
        }

        System.out.println(getSum());
    }

    private static void rotate(int x, int d, int k) {
        int dir = d == 0? -1 : 1;
        starts[x-1] += dir * k;
        starts[x-1] = (starts[x-1] + M) % M;
    }

    private static void execute() {
        int sum = 0;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sum += board[i][j];

                if (i < N-1) {
                    int nextR = (i+1) % N;
                    int currC = (starts[i]+j) % M;
                    int nextC = (starts[nextR]+j) % M;
                    if (board[i][currC] != 0 && board[i][currC] == board[nextR][nextC]) {
                        removes.add(i*M + currC);
                        removes.add(nextR*M + nextC);
                    }
                }

                if (board[i][j] != 0 && board[i][j] == board[i][(j+1)%M]) {
                    removes.add(i*M + j);
                    removes.add(i*M + (j+1)%M);
                } 
            }
       }

       if (removes.isEmpty()) {
        int cnt = getCnt();
        double avg = sum / (double)cnt;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) continue;
                if (board[i][j] > avg) board[i][j]--;
                else board[i][j]++;
            }
        }
       }

       else {
        while(!removes.isEmpty()) {
            int num = removes.poll();
            board[num/M][num%M] = 0;
        }
       }
    }

    private static int getCnt() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != 0) cnt++;
            }
        }
        return cnt;
    }

    private static int getSum() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sum += board[i][j];
            }
        }
        return sum;
    }
    
    private static void print() {
        for (int i = 0; i < N ; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(board[i][(starts[i] + j)%M] + "    ");
            }
            System.out.println();
        }
    }
}
