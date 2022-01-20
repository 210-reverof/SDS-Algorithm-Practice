import java.io.*;
import java.util.*;

// 트라이(Trie)...... 극악 난이도..복습 후 도즈언 하기...

class Main {
    static int w, b;
    static List<String> dic = new ArrayList<>();


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        w = Integer.parseInt(br.readLine());

        for (int i = 0; i < w; i++) {
            dic.add(br.readLine());
        }

        br.readLine();

        b = Integer.parseInt(br.readLine());
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < 4; j++) {
                dic.add(br.readLine());
            }
        }


    }


}