import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ", false);

        int cnt = Integer.parseInt(st.nextToken());
        int num = Integer.parseInt(st.nextToken());

        int[] arr = new int[cnt];
        st = new StringTokenizer(br.readLine(), " ", false);

        // 집어 넣기
        for (int i = 0 ; i < cnt ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 3개 조합
        int result = 0;
        for (int i = 0;i<cnt;i++){
            for (int j = i+1; j< cnt; j++) {    // 그 다음부터 하기
                for(int k = j+1; k <cnt; k++) {
                    int tmp = arr[i] + arr[j] + arr[k];
                    if (tmp > result && tmp <= num) { result = tmp;} // 더 가까우면 대체하기
                }
            }
        }

        br.close();
        bw.write(result + ""); // 숫자 그대로 출력하기
        bw.close();
    }
}
