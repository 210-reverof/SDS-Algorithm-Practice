import java.util.*;

class Solution {
    int[][] tree = new int[1000000][2];
    Queue<Node> nodes = new PriorityQueue<>(Collections.reverseOrder());
    int[][] answer;
    int len, ai = 0;
    public int[][] solution(int[][] nodeinfo) {
        len = nodeinfo.length;
        answer = new int[2][len];
        
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes.add(new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]));
        }
        
        Node curr = nodes.poll();
        tree[1][0] = curr.n;
        tree[1][1] = curr.x;
        while (!nodes.isEmpty()) {
            curr = nodes.poll();
            makeTree(1, curr.n, curr.x);
        }
        
        preorder(1);
        ai = 0;
        postorder(1);
        
        return answer;
    }
    
    private void makeTree(int parentIdx, int currN, int currX) {
        int nextIdx = currX < tree[parentIdx][1]? parentIdx * 2 : parentIdx * 2 + 1;
        
        if (tree[nextIdx][0] == 0) {
            tree[nextIdx][0] = currN;
            tree[nextIdx][1] = currX;
        }
        else makeTree(nextIdx, currN, currX);        
    }
    
    private void preorder(int idx) {
        if (idx <= 10000 && tree[idx][0] != 0) answer[0][ai++] = tree[idx][0];
        if (idx*2 <= 10000 && tree[idx*2][0] != 0) preorder(idx*2);
        if (idx*2 + 1 <= 10000 && tree[idx*2 + 1][0] != 0) preorder(idx*2 + 1);
    }
    
    private void postorder(int idx) {
        if (idx*2 <= 10000 && tree[idx*2][0] != 0) postorder(idx*2);
        if (idx*2 + 1 <= 10000 && tree[idx*2 + 1][0] != 0) postorder(idx*2 + 1);
        if (idx <= 10000 && tree[idx][0] != 0) answer[1][ai++] = tree[idx][0];
    }

    class Node implements Comparable<Node> {
        int n, x, y;
        
        public Node(int n, int x, int y) {
            this.n = n;
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int compareTo(Node node) {
            if (this.y != node.y) return this.y - node.y;
            return this.x - node.x;
        }
        
        @Override
        public String toString() {
            return n + " (" + x + ", " + y + ")";
        }
    }
}
