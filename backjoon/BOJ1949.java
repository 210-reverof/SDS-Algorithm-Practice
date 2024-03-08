import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1949 {

    private static int N;
    private static int[] populations;
    private static boolean[] visited;
    private static int[][] dp;
    private static List<Integer>[] tree;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        populations = new int[N+1];
        visited = new boolean[N+1];
        tree = new List[N+1];
        dp = new int[N+1][2];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            tree[i] = new LinkedList<>();
            populations[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        checkAndCount(1);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void checkAndCount(int num) {
        visited[num] = true;

        dp[num][0] += populations[num];
        for (int next: tree[num]) {
            if (visited[next]) continue;
            checkAndCount(next);

            dp[num][0] += dp[next][1];
            dp[num][1] += Math.max(dp[next][0], dp[next][1]);
        }
    }
}
