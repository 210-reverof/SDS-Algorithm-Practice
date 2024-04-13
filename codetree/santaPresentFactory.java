import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static Set<Integer> broken = new HashSet<>(); 
    static LinkedList<Integer>[] belts;
    static int[] starts;
    static Map<Integer, int[]> info = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int Q = Integer.parseInt(br.readLine());
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if (cmd.equals("100")) {
                N = Integer.parseInt(st.nextToken());
                M = Integer.parseInt(st.nextToken());
                int size = N/M;

                belts = new LinkedList[M];
                starts = new int[M];

                for (int i = 0; i < M; i++) {
                    belts[i] = new LinkedList<>();
                    for (int j = 0; j < size; j++) {
                        belts[i].add(Integer.parseInt(st.nextToken()));
                    }
                }

                for (int i = 0; i < M; i++) {
                    for (int j = 0; j < size; j++) {
                        int w = Integer.parseInt(st.nextToken());
                        info.put(belts[i].get(j), new int[]{i, w});
                    }
                }                
            }

            else if (cmd.equals("200")) {
                sb.append(downBox(Integer.parseInt(st.nextToken())) + "\n");
            }

            else if (cmd.equals("300")) {
                sb.append(removeBox(Integer.parseInt(st.nextToken())) + "\n");
            }

            else if (cmd.equals("400")) {
                sb.append(checkBox(Integer.parseInt(st.nextToken())) + "\n");
            }

            else {
                sb.append(breakBox(Integer.parseInt(st.nextToken())) + "\n");
            }
        }

        System.out.println(sb);
    }

    private static int breakBox(int belt) {
        if (broken.contains(belt)) return -1;
        broken.add(belt);

        int targetBelt = 0;
        for (int i = 0; i < M; i++) {
            if (!broken.contains(i+1)) {
                targetBelt = i;
                break;
            }
        }

        int size = belts[targetBelt].size();
        int lastIdx = (starts[targetBelt] + size - 1) % size;
        LinkedList<Integer> currBelt = belts[belt-1];
        int currSize = currBelt.size(); 
        boolean isStartBack = lastIdx > starts[targetBelt];       
        for (int i = 0; i < currSize; i++) {
            int n = currBelt.get((starts[belt-1]+i)%currSize);
            int[] curr = info.get(n);
            curr[0] = targetBelt;
            belts[targetBelt].add(++lastIdx, n);
            starts[targetBelt]++;
        }
        belts[belt-1].clear();
        return belt;
    }

    private static int checkBox(int id) {
        if (!info.containsKey(id)) return -1;

        int[] box = info.get(id);
        int idx = 0;
        for (int n: belts[box[0]]) {
            if (n == id) break;
            idx++;
        }

        starts[box[0]] = idx;
        return box[0] + 1;
    }

    private static int removeBox(int id) {
        if (!info.containsKey(id)) return -1;

        int[] box = info.get(id);
        int idx = 0;
        for (int n: belts[box[0]]) {
            if (n == id) break;
            idx++;
        }
      
        if (starts[box[0]] >= idx) starts[box[0]]--;
        belts[box[0]].remove(idx);
        info.remove(box);
        return id;
    }

    private static int downBox(int max) {
        int sum = 0;
        for (int i = 0; i < M; i++) {
            if (broken.contains(i+1)) continue;
            
            int firstIdx = belts[i].get(starts[i]);
            int first = info.get(firstIdx)[1];
            if (first <= max) {
                sum += first;

                info.remove(firstIdx);
                belts[i].remove(starts[i]);
            }

            else {
                starts[i] = (starts[i] + 1) % 4;
            }
        }
        
        return sum;
    }    

    private static void print() {
        System.out.println(Arrays.toString(starts));
        for (int i = 0; i < M; i++) {
            int size = belts[i].size();
            for (int j = 0; j < size; j++) {
                System.out.print(belts[i].get((starts[i] + j)%size) + "(" + info.get(belts[i].get((starts[i] + j)%size))[1] + ") ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
