import java.io.*;
import java.util.*;

// 슬라이딩 윈도우
// sum == M : L++
// sum < M : H++
// sum > M : L++

class Main {
	static int N; // 숫자 개수
	static int S; // 합
    static int[] arr; // 합
	static int min;  // 결과

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st  = new StringTokenizer(br.readLine(), " ", false);

		N = Integer.parseInt(st.nextToken());
		S =  Integer.parseInt(st.nextToken());
        arr = new int[N];
        min = -1;

		st = new StringTokenizer(br.readLine(), " ", false);

        // 배열에 할당
		for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int L = 0, H = 0;
        int sum = 0;
        while(true){
            if(sum >= S){
                sum -= arr[L];
                min = min==-1? H- L: Math.min(min, (H- L));
                L++;
            } else if(H == N) {
                break;
            }else{
                sum += arr[H];
                H++;
            }
        }

        if (min == -1 ) bw.write("0");
        else bw.write(min + "");
		bw.close();
	}
}
