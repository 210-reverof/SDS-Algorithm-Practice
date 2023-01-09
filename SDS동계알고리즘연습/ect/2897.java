import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[][] map = new char[50][50];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        for(int i = 0; i < R; i++){
            String input = br.readLine();
            for(int j = 0; j < C; j++){
                map[i][j] = input.charAt(j);
            }
        }
        int zero = 0, one = 0, two = 0, three = 0, four = 0;
        for(int i = 0; i < R - 1; i++){
            for(int j = 0; j < C - 1; j++){
                int cnt = 0;
                base:
                for(int k = i; k < i + 2; k++){
                    for(int l = j; l < j + 2; l++){
                        if(map[k][l] == '#') break base;
                        if(map[k][l] == 'X') cnt++;
                        if(k == i + 1 && l == j + 1){
                            if(cnt == 0) zero++;
                            else if(cnt == 1) one++;
                            else if(cnt == 2) two++;
                            else if(cnt == 3) three++;
                            else if(cnt == 4) four++;
                        }
                    }
                }
            }
        }
        bw.write(zero + "\n");
        bw.write(one + "\n");
        bw.write(two + "\n");
        bw.write(three + "\n");
        bw.write(four + "\n");

        bw.flush();
        br.close();
        bw.close();
    }
}
