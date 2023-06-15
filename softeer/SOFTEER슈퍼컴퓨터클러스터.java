import java.util.*;
import java.io.*;

public class Main {
    static Map<Long, Integer> map = new HashMap<>();
    static Queue<Long> pq = new PriorityQueue<>();
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long n = Long.parseLong(st.nextToken());
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        for (Long k : map.keySet())
            pq.add(k);
        
        long answer = pq.poll();
        long a = map.get(answer), b = answer * a, c = answer * answer * a;
        while(!pq.isEmpty()) {
            long next = pq.poll();
            long aa = map.get(next);
            long bb = map.get(next) * next;
            long cc = next * next * map.get(next);

            long cost = getCost(a + aa, b + bb, c + cc, next);
            if (cost > B)break;

            a += aa;
            b += bb;
            c += cc;
            answer = next;
            if (cost == B) break;
        }
        while (getCost(a, b, c, answer+1) <= B) answer++;

        System.out.println(answer);
    }

    private static long getCost(long a, long b, long c, long next) {
        return a * next * next - 2 * next * b + c;
    }
}
