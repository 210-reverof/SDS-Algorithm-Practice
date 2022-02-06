import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class Main {
    private static int n;
    private static ArrayList<Integer>[] con;
    private static ArrayList<Integer>[] conv;
    private static PriorityQueue<Integer>[] D;
    private static int k;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
 
        st = new StringTokenizer(br.readLine().trim());
 
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
 
        con = new ArrayList[n + 1];
        conv = new ArrayList[n + 1];
 
        for (int i = 1; i <= n; i++) {
            con[i] = new ArrayList<Integer>();
            conv[i] = new ArrayList<Integer>();
        }
 
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine().trim());
 
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
 
            con[a].add(b);
            conv[a].add(c);
        }
        KthDijkstra(1);
 
        for (int i = 1; i <= n; i++) {
            if (D[i].size() < k)
                System.out.println(-1);
            else {
                System.out.println(D[i].poll());
            }
        }
    }
 
    private static void KthDijkstra(int a) {
        D = new PriorityQueue[n + 1];
        for (int i = 1; i <= n; i++) {
            D[i] = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
                @Override
                public int compare(Integer a, Integer b) {
                    return b - a;
                }
            });
        }
        D[a].add(0);
        PriorityQueue<int[]> que = new PriorityQueue<int[]>(10, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
 
        que.add(new int[] { 0, a });
        while (!que.isEmpty()) {
            int q = que.peek()[1];
            int d = que.peek()[0];
 
            que.poll();
            for (int i = 0; i < con[q].size(); i++) {
                int j = con[q].get(i);
                int v = conv[q].get(i);
                int c = d + v;
 
                if (D[j].size() < k) {
                    D[j].add(c);
                    que.add(new int[] { c, j });
                } else if (c < D[j].peek()) {
                    D[j].poll();
                    D[j].add(c);
                    que.add(new int[] { c, j });
                }
            }
        }
    }
}
