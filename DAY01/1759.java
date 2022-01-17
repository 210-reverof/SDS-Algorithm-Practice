import java.io.*;
import java.util.*;

class Main {
    static char[] arr;
    static int[] result;
    static int len;
    static int num;
    
    // 1. 체크인
    // 2. 목적지? (길이 + 자음, 모음)
    // 3. 연결
    //      4. 갈 수?
    //      5. 간다
    // 6. 체크아웃

    // 오름차순, 이미 선택x, (최소 한 개 모음, 최소 두 개 자음 => 2.목적지인가에 대한 조건)
    // 오름차순과 체크인아웃을 한번에? -> 입력 알파벳들을 정렬
    // 체크인아웃 이유? 실제로 연결이 되어있지만, 다시 돌아갈 수 없도록 해놓는다
    // String 더하기 연산은 실제로는 느리다 (퍼포먼스가 안 좋다)
    // Arrays.toString(arr) 이런 식으로 배열 문자열로 출력하기

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ", false);

        len = Integer.parseInt(st.nextToken());
        num = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ", false);
        arr = new char[num];
        result = new int[num];

        for (int i = 0 ; i< num; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);
        PW(0, 0, 0, 0);
    }

    public static void PW(int start, int depth, int con, int vow) {
 
        for (int i = start; i < num; i++) {
            result[i] = 1; // 체크인
            int isVow = 0;
            int isCon = 0;

            // 목적지인지
            if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') isVow = 1;
            else isCon = 1;

            // 순회
            PW(i + 1, depth + 1, con + isCon, vow + isVow);
 
            result[i] = 0;
        }

        if (depth == len && con >= 2 && vow >= 1) {
            print();
        }
    }

    public static void print() {
        for (int i = 0; i < num; i++) {
            if (result[i] == 1)
                System.out.print(arr[i]);
        }
        System.out.println();
    }
}
