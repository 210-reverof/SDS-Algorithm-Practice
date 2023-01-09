import java.io.*;
import java.util.*;

class Main {
    static int N; // 보석 총 개수
    static int K; // 가방 개수
    static List<Jewel> jewels = new ArrayList<>();
    static List<Integer> bags = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ", false);

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 보석 정보 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ", false);
            jewels.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        // 가방 정보 받기
        for (int i = 0; i < K; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }

        // 가방 오름차순 정렬
        Collections.sort(bags);

        // 보석 무게 오름차순 정렬
        Collections.sort(jewels, Comparator.comparing(Jewel::getM));

        // 보석 가격이 높은값 기준 힙 (힙의 탑 : 가장 비싼 보석)
        PriorityQueue<Jewel> pq = new PriorityQueue<>(Comparator.comparing(Jewel::getV).reversed());

        int jIndex = 0;
        long result = 0;

        // 1. 남은 가방 중 제일 작은 가방을 선택. <- 정렬
        for (int i = 0; i<bags.size(); i++) {
            int bag = bags.get(i);

            // 2. 선택된 가방에 넣을 수 있는 남은 보석 중 가장 비싼 보석을 선택 <- 힙을 사용
            while (jIndex < N && jewels.get(jIndex).M <= bag) {
                pq.add(jewels.get(jIndex++));
            }
            if (!pq.isEmpty()) {    // 넣을 수 있는 보석이 존재하면
                result += pq.poll().V;
            }
        }

        bw.write(result + "");
        bw.close();
    }
}

class Jewel {
    int M;  // 무게
    int V;  // 가격

    public Jewel(int M, int V) {
        this.M = M;
        this.V = V;
    }

    public int getM() {
        return M;
    }
    
    public int getV() {
        return V;
    }

}
