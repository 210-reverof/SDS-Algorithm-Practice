import java.io.*;
import java.util.*;

// Indexed Tree 활용
// 특정 구간에 대한 연산이 필요한 경우 인덱스 트리가 매우 유용
// 인덱스 맛 정보, 값은 개수
// 재귀호출에 익숙해질 필요가 있는 듯,,

class Main {
    static int[] tree;
    static int N, S;
    static int right = 1000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        S = 1;
        while (S<right) S*=2;
        tree = new int[S*2];
        //Arrays.fill(tree, 0);

        N = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ", false);
            int A,B,C;
            A = Integer.parseInt(st.nextToken());

            if (A == 1) {
                System.out.println("--------------------1");
                // 사탕을 꺼내는 경우, 
                // B는 꺼낼 사탕의 순위(인덱스)
                B = Integer.parseInt(st.nextToken());
                int targetIndex = query(1, S, 1, B);
                update(1,S,1,targetIndex,-1);
                System.out.println(targetIndex);
                // UPDATE(index, -1)

            }

            else if (A==2) {
                System.out.println("--------------------2");
                // 사탕을 넣는 경우
                // B는 넣을 사탕의 맛 정보, C는 그러한 사탕의 개수 (양수 : 넣는거, 음수면 빼는 거)
                B = Integer.parseInt(st.nextToken());
                C = Integer.parseInt(st.nextToken());
                update(1, S , 1, B, C);

            }
            
        }
    }

    // 왼쪽 >= Q : Q(왼쪽)
    // 왼쪽 < Q : Q(Q-left)
    static int query(int left, int right, int node, int cnt) {
        // 1. Leaf에 도착했을 때 -> 사탕 번호 반환
        if(left == right) return left;

        int mid = (left + right) / 2;

        // 2. 왼쪽 >= cnt -> 왼쪽으로 이동
        if(tree[node*2] >= cnt) {
            return query(left, right, node*2, cnt);
        }

        // 3. 왼쪽 < cnt
        else {
            return query(mid+1, right, node*2+1,cnt-tree[node*2]);
        }
    }

    // TD
    static void update(int left, int right, int node, int target, long diff) {
        if (target < left || right > target) return;

        else {
            tree[node] += diff;
            if (left != right) {
                int mid = (left + right) / 2;
                update(left, mid, node*2, target, diff);
                update(mid+1, right, node*2+1, target, diff);
            }
            else return;
        }
    }
}