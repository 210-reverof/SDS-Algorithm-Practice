import java.io.*;
import java.util.*;
 
class Main {
    static final int  right = 1000000;
    static long[] tree = new long[right * 4];
    static long[] arr;
    static int N;

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine(), " ", false);
            for (int i = 0; i < N; i++) {
                arr[i] = Long.parseLong(st.nextToken());
            }
 
            int nodeIdex = getIndex(N);
            init(nodeIdex, N);
            cal(nodeIdex, N);
        }
    }
 
    private static void cal(int first, int n) {
        long max = 0;
        long val = 0;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            long curNum = arr[i];
 
            update(first, i, 0);

            if (arr[i] % tree[1] != 0 && max < tree[1]) {
                max = tree[1];
                val = arr[i];
            }
 
            if (arr[i] % tree[1] == 0) {
                cnt++;
            }

            update(first, i, curNum);
        }
 
        if (cnt == n) {
            System.out.println("-1");
        } else {
            System.out.println(max + " " + val);
        }
    }
 
    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private static int getIndex(long n) {
        int ret = 1;
        while (ret < n) {
            ret *= 2;
        }
        return ret;
    }
 
    private static void init(int nodeIdex, int n) {
        for (int i = nodeIdex; i < nodeIdex + n; i++) {
            tree[i] = arr[i - nodeIdex];
        }
        for (int i = nodeIdex - 1; i >= 1; i--) {
            tree[i] = gcd(tree[i * 2], tree[i * 2 + 1]);
        }
    }
 
    private static void update(int first, int target, long val) {
        int tmpIdx = first + target;
        tree[tmpIdx] = val;
        while (tmpIdx >= 1) {
            tmpIdx /= 2;
            tree[tmpIdx] = gcd(tree[tmpIdx * 2], tree[tmpIdx * 2 + 1]);
        }
    }
}