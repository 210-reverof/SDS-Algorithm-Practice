import java.util.*;

class Solution {
    int[][] tree = new int[10001][4];    // n, left, x, right
    Queue<Node> nodes = new PriorityQueue<>(Collections.reverseOrder());
    int[][] answer;
    int len, ai = 0;
    public int[][] solution(int[][] nodeinfo) {
        len = nodeinfo.length;
        answer = new int[2][len];
        
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes.add(new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]));
        }
        
        makeTree();
    
        preorder(1);
        ai = 0;
        postorder(1);
        
        return answer;
    }
    
    private void makeTree() {
        Node node = nodes.poll();
        tree[1] = new int[]{node.n, 0, node.x, 100000};
        int prevY = node.y;
        int level = 1;
        
        while (!nodes.isEmpty()) {
            node = nodes.poll();
            if (node.y != prevY) {
                prevY = node.y;
                level++;
            }
            
            int tmpIdx = (int) Math.pow(2, level-2);
            while(tmpIdx < Math.pow(2, level-1)) {
                int tmpLeft = tree[tmpIdx][1];
                int tmpX = tree[tmpIdx][2];
                int tmpRight = tree[tmpIdx][3];
                
                if (tmpLeft <= node.x && node.x <= tmpRight) {
                    if (node.x <= tmpX) {
                        tree[tmpIdx*2] = new int[]{node.n, tmpLeft, node.x, tmpX};
                        break;
                    }
                    
                    tree[tmpIdx*2+1] = new int[]{node.n, tmpX, node.x, tmpRight};
                    break;
                }
                
                tmpIdx++;
            }
        }
    }
    
    private void preorder(int idx) {
        answer[0][ai++] = tree[idx][0];
        if (idx*2 <= 10000 && tree[idx*2][0] != 0) preorder(idx*2);
        if (idx*2 + 1 <= 10000 && tree[idx*2 + 1][0] != 0) preorder(idx*2 + 1);
    }
    
    private void postorder(int idx) {
        if (idx*2 <= 10000 && tree[idx*2][0] != 0) postorder(idx*2);
        if (idx*2 + 1 <= 10000 && tree[idx*2 + 1][0] != 0) postorder(idx*2 + 1);
        answer[1][ai++] = tree[idx][0];
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
    }
}
