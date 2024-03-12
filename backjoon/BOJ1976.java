import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1976 {
    public static void main(String[] main) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        char[][] graph = new char[N][N];
        int[] route = new int[M];
        
        for (int i = 0; i < N; i++) {
            graph[i] = br.readLine().replace(" ", "").toCharArray();
            graph[i][i] = '1';
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (graph[i][j] == '1') continue;
                    if (graph[i][k] == '1' && graph[k][j] == '1') {
                        graph[i][j] = '1';
                        graph[j][i] = '1';
                    }
                }
            }
        }

        String answer = "YES";
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < M; i++) {
            route[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int i = 1; i < M; i++) {
            if (graph[route[i]][route[i-1]] == '0') {
                answer = "NO";
                break;
            }
        }

        System.out.println(answer);
    }
}