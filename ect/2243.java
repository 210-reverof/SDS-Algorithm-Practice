import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
    static final int MAX = 1000000;
    static int tree[] = new int[(MAX + 1) * 4];
    static int N;
 
    static void update(int node, int s, int e, int idx, int diff) {
        if (idx < s || idx > e)
            return;
 
        if (s == e) {
            tree[node] += diff;
            return;
        }
 
        tree[node] += diff;
        int m = (s + e) / 2;
        update(node * 2, s, m, idx, diff);
        update(node * 2 + 1, m + 1, e, idx, diff);
    }
 
    static int binarysearch(int node, int l, int r, int x) {
        if (l == r) {
            update(1, 1, MAX, l, -1);
            return l;
        }
 
        int m = (l + r) / 2;
        if (tree[node * 2] >= x)
            return binarysearch(node * 2, l, m, x);
        else
            return binarysearch(node * 2 + 1, m + 1, r, x - tree[node * 2]);
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();
 
        N = Integer.parseInt(st.nextToken());
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
 
            if (type == 1) {
                int a = Integer.parseInt(st.nextToken());
                sb.append(binarysearch(1, 1, MAX, a)).append("\n");
            } else {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
 
                update(1, 1, MAX, a, b);
            }
        }
 
        System.out.println(sb.toString());
    }
}
