import java.io.*;
import java.util.*;

// 슬라이딩 윈도우
// sum == M : L++
// sum < M : H++
// sum > M : L++

class Main {
	static int N; // 숫자 개수
	static int M; // 합
    static int[] arr; // 합
	static int result;  // 결과

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st  = new StringTokenizer(br.readLine(), " ", false);

		N = Integer.parseInt(st.nextToken());
		M =  Integer.parseInt(st.nextToken());
        arr = new int[N];
        result = 0;

		st = new StringTokenizer(br.readLine(), " ", false);

        // 배열에 할당
		for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int L = 0, H = 0;
        int sum = 0;
        while (L<=H && H<=N) {
            // sum == M : L++, result++
            if( sum >= M ) { sum -= arr[L++];}

            else if(H == N) break;

            // sum < M : H++
            else if ( sum < M ) sum += arr[H++];

            // sum > M : L++
            //else sum -= arr[L++];

            if (sum == M) result++;
        }

        bw.write(result + "");
		bw.close();
	}
}
