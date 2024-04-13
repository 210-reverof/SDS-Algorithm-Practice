import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] beltInfo;
    static Map<Integer, Box> boxes = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int Q = Integer.parseInt(br.readLine());
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if (cmd.equals("100")) {
                N = Integer.parseInt(st.nextToken());
                M = Integer.parseInt(st.nextToken());

                beltInfo = new int[3][N+1];
                Arrays.fill(beltInfo[0], -1);
                Arrays.fill(beltInfo[1], -1);

                for (int i = 1; i <= M; i++) {
                    int beltNum = Integer.parseInt(st.nextToken());
                    
                    boxes.put(i, new Box(beltNum, beltInfo[1][beltNum], -1));
                    if (beltInfo[1][beltNum] != -1) boxes.get(beltInfo[1][beltNum]).next = i;

                    if (beltInfo[0][beltNum] == -1) beltInfo[0][beltNum] = i;
                    beltInfo[1][beltNum] = i;
                    beltInfo[2][beltNum]++;
                }            
            }

            else if (cmd.equals("200")) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                sb.append(moveBox(a, b) + "\n");                
            }

            else if (cmd.equals("300")) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                sb.append(changeFirstBox(a, b) + "\n"); 
            }

            else if (cmd.equals("400")) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                sb.append(devideBox(a, b) + "\n"); 
            }

            else if (cmd.equals("500")) {
                sb.append(getBoxInfo(Integer.parseInt(st.nextToken())) + "\n");   
            }

            else {
                sb.append(getBeltInfo(Integer.parseInt(st.nextToken())) + "\n"); 
            }
        }

        System.out.println(sb);
    }

    private static int getBoxInfo(int n) {
        Box box = boxes.get(n);
        return box.prev + (box.next * 2);
    }


    private static int getBeltInfo(int n) {
        return beltInfo[0][n] + (beltInfo[1][n] * 2) + (beltInfo[2][n] * 3);
    }

    private static int devideBox(int originBelt, int targetBelt) {
        if (beltInfo[2][originBelt] <= 1) return beltInfo[2][targetBelt];
        int cnt = beltInfo[2][originBelt] / 2;
        int oHead = beltInfo[0][originBelt];
        int oLast = getNumByCnt(oHead, cnt);
        int oLastNext = boxes.get(oLast).next; 
        int tHead = beltInfo[0][targetBelt];

        boxes.get(oLastNext).prev = -1;
        beltInfo[0][originBelt] = oLastNext;

        boxes.get(oLast).next = beltInfo[0][targetBelt];
        beltInfo[0][targetBelt] = oHead;
        if (beltInfo[2][targetBelt] == 0) beltInfo[1][targetBelt] = oLast;
        else {
            boxes.get(beltInfo[1][targetBelt]).prev = oLast;
        }

        beltInfo[2][originBelt] -= cnt;
        beltInfo[2][targetBelt] += cnt;
        return beltInfo[2][targetBelt];
    }

    private static int getNumByCnt(int start, int cnt) {
        int currNum = start;
        int currCnt = cnt;
        while (currCnt-- > 1) {
            currNum = boxes.get(currNum).next;
        }
        return currNum;
    }

    private static int moveBox(int originBelt, int targetBelt) {
        if (beltInfo[0][originBelt] == -1) return beltInfo[2][targetBelt];
        
        int oTail = beltInfo[1][originBelt];
        int tHead = beltInfo[0][targetBelt];

        if (tHead == -1) {
            beltInfo[0][targetBelt] = beltInfo[0][originBelt];
            beltInfo[1][targetBelt] = oTail;
            beltInfo[0][originBelt] = beltInfo[1][originBelt] = -1;
        
        } else {
            boxes.get(oTail).next = tHead;
            boxes.get(tHead).prev = oTail;

            beltInfo[0][targetBelt] = beltInfo[0][originBelt];

            beltInfo[0][originBelt] = beltInfo[1][originBelt] = -1;
        }

        beltInfo[2][targetBelt] += beltInfo[2][originBelt]; 
        beltInfo[2][originBelt] = 0;
        return beltInfo[2][targetBelt];
    }

    private static int changeFirstBox(int originBelt, int targetBelt) {
        if (beltInfo[2][originBelt] == 0 && beltInfo[2][targetBelt] == 0) return beltInfo[2][targetBelt];

        if (beltInfo[2][originBelt] == 0) moveFirstToEmpty(targetBelt, originBelt); 
        else if (beltInfo[2][targetBelt] == 0) moveFirstToEmpty(originBelt, targetBelt);
        else {
            int oHead = beltInfo[0][originBelt];
            int oSecond = boxes.get(oHead).next;
            int tHead = beltInfo[0][targetBelt];
            int tSecond = boxes.get(tHead).next;

            boxes.get(oHead).next = tSecond;
            boxes.get(tHead).next = oSecond;
            if (oSecond != -1) boxes.get(oSecond).prev = tHead;
            if (tSecond != -1) boxes.get(tSecond).prev = oHead;

            beltInfo[0][originBelt] = tHead;
            beltInfo[0][targetBelt] = oHead;
            
            if (beltInfo[2][originBelt] <= 1) beltInfo[1][originBelt] = beltInfo[0][originBelt];
            if (beltInfo[2][targetBelt] <= 1) beltInfo[1][targetBelt] = beltInfo[0][targetBelt]; 
        }

        return beltInfo[2][targetBelt];
    }

    private static void moveFirstToEmpty(int originBelt, int targetBelt) {
        int oHead = beltInfo[0][originBelt];
        int oSecond = boxes.get(oHead).next;

        beltInfo[2][originBelt]--;
        beltInfo[2][targetBelt]++;
        
        beltInfo[0][targetBelt] = beltInfo[1][targetBelt] = beltInfo[0][originBelt];
        boxes.get(oHead).next = -1;

        beltInfo[0][originBelt] = oSecond;
        if (beltInfo[2][originBelt] <= 1) beltInfo[1][originBelt] = beltInfo[0][originBelt]; 

        if (oSecond == -1) return;
        boxes.get(oSecond).prev = -1;
    }

    private static void print() {
        for (int i = 1; i <= N; i++) {
            int curr = beltInfo[0][i];
            System.out.print(beltInfo[2][i] + ":: ");
            while(curr != -1) {
                Box currBox = boxes.get(curr);
                // System.out.print(curr + " ");
                System.out.print("[" +currBox.prev + "]" + curr + "[" + currBox.next + "]    ");
                curr = currBox.next;
            }
            System.out.println();
        }
        System.out.println("-----------------");
    }

    private static class Box {
        int beltNum, prev, next;

        public Box(int b, int p, int n) {
            this.beltNum = b;
            this.prev = p;
            this.next = n;
        }

        @Override
        public String toString() {
            return "num:" + beltNum + " p:" + prev + " n:" + next;  
        }
    }    
}
