import java.io.*;
import java.util.*;

// 일반적인 배열 부분합 아님
// sub배열 구하기
// N^2 이지만, 시간복잡도 뒤쪽의 부분을 최대한 줄이는 것

class Main {
    static int T, ALen, BLen; // 기준 합, A길이, B길이
    static long[] A, B;
    static List<Long> SubA = new ArrayList<>();
    static List<Long> SubB = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        ALen = Integer.parseInt(br.readLine());
        A = new long[ALen];
        st = new StringTokenizer(br.readLine(), " ", false);
        for (int i = 0; i < ALen; i++)
            A[i] = Long.parseLong(st.nextToken());

        BLen = Integer.parseInt(br.readLine());
        B = new long[BLen];
        st = new StringTokenizer(br.readLine(), " ", false);
        for (int i = 0; i < BLen; i++)
            B[i] = Long.parseLong(st.nextToken());

        for (int i = 0; i < ALen; i++) { // subA 만들기
            long tmp = 0;
            for (int j = i; j < ALen; j++) {
                tmp += A[j];
                SubA.add(tmp);
            }
        }

        for (int i = 0; i < BLen; i++) {    // subB 만들기
            long tmp = 0;
            for (int j = i; j < BLen; j++) {
                tmp += B[j];
                SubB.add(tmp);
            }
        }

        // 두 서브배열 정렬
        SubA.sort(null);
        SubB.sort(null);

        int pa = 0, pb = SubB.size()-1;
        Long sum;
        int result = 0;

        while(pa < SubA.size() && pb >= 0) {
            sum = SubA.get(pa) + SubB.get(pb);

            // 만약 T와 일치한다면 : (pa++, pb-- ~)
            if (sum == T) {
                // A에서 같은 개수
                int tmpA = 1;
                for (int i = 1; pa+i < SubA.size(); i++) {
                    if (SubA.get(pa) == SubA.get(pa+i)) {
                        tmpA++;
                    }
                    else break;
                }
                pa += tmpA;

                // B에서 같은 개수
                int tmpB = 1;
                for (int i = 1; pb-i >= 0; i++) {
                    if (SubA.get(pb) == SubA.get(pb-i)) tmpB++;
                    else break;
                }
                pb -= tmpB;

                result += tmpA*tmpB;
            }

            // sum < T : pa++
            else if ( sum < T ) pa++;

            // sum > T : pb--
            else pb--;
        }

        bw.write(result + "");
        bw.close();
    }
}
