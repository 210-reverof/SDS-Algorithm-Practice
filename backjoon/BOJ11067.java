import java.util.*;
import java.io.*;

public class BOJ11067 {
    static int N;
    static Map<Integer, List<Integer>> groups = new HashMap<>();
    static Queue<Integer> xq = new PriorityQueue<>();
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            groups = new HashMap<>();
            xq = new PriorityQueue<>();

            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                if (!groups.containsKey(x)) {
                    xq.add(x);
                    groups.put(x, new LinkedList<>());
                }
                groups.get(x).add(y);
            }

            List<int[]> route = getRoute();

            st = new StringTokenizer(br.readLine());
            int outCnt = Integer.parseInt(st.nextToken());
            for (int i = 0; i < outCnt; i++) {
                int idx = Integer.parseInt(st.nextToken());
                int[] currVal = route.get(idx-1);
                sb.append(currVal[0] + " " + currVal[1] + "\n");
            }
        }
        System.out.println(sb);
    }

    private static List<int[]> getRoute() {
        List<int[]> route = new LinkedList<>();

        int currX, privY = 0;

        while (!xq.isEmpty()) {
            currX = xq.poll();

            List<Integer> nums = groups.get(currX);
            Collections.sort(nums);

            if (nums.get(0) == privY) {
                for (int num : nums) {
                    route.add(new int[]{currX, num});
                }
                privY = nums.get(nums.size() - 1);

            } else {
                for (int i = nums.size() - 1; i >= 0; i--) {
                    route.add(new int[]{currX, nums.get(i)});
                }
                privY = nums.get(0);
            }
        }

        return route;
    }
}
