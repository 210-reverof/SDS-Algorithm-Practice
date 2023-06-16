import java.util.*;
import java.io.*;

public class Main {
    static Map<Character, Integer> board;
    static char[][] arr = new char[5][5];
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] msg = br.readLine().toCharArray();
        char[] key = br.readLine().toCharArray();
        board = getBoard(key);
        
        for (int i = 0; i < msg.length; i+=2) {
            char a, b;
            if (i == msg.length - 1) {
                a = msg[i];
                b = 'X';

            } else if (msg[i] != msg[i+1]) {
                a = msg[i];
                b = msg[i+1];

            } else {
                if (msg[i] == 'X') {
                    a = 'X';
                    b = 'Q';

                } else {
                    a = msg[i];
                    b = 'X';
                }
                i--;
            }

            char[] code = getCode(a,b);
            sb.append(code);
        }

        System.out.println(sb);
    }

    private static char[] getCode(char a, char b) {
        int ar = board.get(a) / 5, ac = board.get(a) % 5, br = board.get(b) / 5, bc = board.get(b) % 5;

        if (ar == br) return new char[]{arr[ar][(ac + 1) % 5], arr[ar][(bc + 1) % 5]};
        if (ac == bc) return new char[]{arr[(ar + 1) % 5][ac], arr[(br + 1) % 5][ac]};
        return new char[]{arr[ar][bc], arr[br][ac]};
    }

    private static Map<Character, Integer> getBoard(char[] key) {
        Set<Character> chars = new TreeSet<>();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < 26; i++) chars.add((char)('A' + i));
        chars.remove('J');

        int idx = 0;
        for (char k : key) {
            if (chars.contains(k)) {
                arr[idx/5][idx%5] = k;
                map.put(k, idx++);
                chars.remove(k);
            }
        }
        for (Character c : chars) {
            arr[idx/5][idx%5] = c;
            map.put(c, idx++);
        }

        return map;
    } 
}
