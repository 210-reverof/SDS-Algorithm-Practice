import java.io.*;
import java.util.*;

public class BOJ2533 {
    static List<Integer>[] tree;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        tree = new List[n+1];
        visited = new boolean[n+1];

        for (int i = 1; i <= n; i++) tree[i] = new LinkedList<>();

        int root = 0;
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (i == 0) {
                root = a;
            }

            tree[a].add(b);
            tree[b].add(a);
        }

        int[] rootStat = getRootStat(root);
        System.out.println(Math.min(rootStat[0], rootStat[1]));
    }

    private static int[] getRootStat(int num) {
        visited[num] = true;
        if (tree[num].isEmpty()) {
            return new int[]{0, 1};
        }

        int[] min = new int[]{0, 1};

        for (int n: tree[num]) {
            if (visited[n]) continue;
            int[] currMin = getRootStat(n);
            min[0] += currMin[1];
            min[1] += Math.min(currMin[0], currMin[1]);
        }

        return min;
    }
}
