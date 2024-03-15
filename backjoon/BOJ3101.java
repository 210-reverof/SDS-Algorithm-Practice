import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ3101 {

    static int[][] ds = {{0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}, {-1,0}, {-1,1}};
    static int N, K;
    static int[][] board;
    public static void main(String[] agrs) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        fill();

        int answer = 1, r = 0, c = 0;
        char[] route = br.readLine().toCharArray();
        for(char curr: route) {
            int currD = getDir(curr);
            r += ds[currD][0];
            c += ds[currD][1];
            answer += board[r][c];
        }
        System.out.println(answer);
    }

    private static int getDir(char d) {
        if (d == 'R') return 0;
        if (d == 'D') return 2;
        if (d == 'L') return 4;
        return 6;
    }

    private static void fill() {
        int size = N*N, currD = 7;
        int leftR = 0, leftC = 0, rightR = N-1, rightC = N-1;

        if (N % 2 == 1) {
            board[N/2][N/2] = size/2 + 1;
        }

        for (int i = 1; i <= size/2; i++) {
            board[leftR][leftC] = i;
            board[rightR][rightC] = size-i+1;

            if (currD == 7 && leftR == 0) {
                board[leftR][++leftC] = ++i;
                board[rightR][--rightC] = size-i+1;
                currD = 3;
            }

            if (currD == 3 && leftC == 0) {
                board[++leftR][leftC] = ++i;
                board[--rightR][rightC] = size-i+1;
                currD = 7;
            }

            leftR += ds[currD][0];
            leftC += ds[currD][1];
            rightR += ds[(currD+4)%8][0];
            rightC += ds[(currD+4)%8][1];
        }
    }
}