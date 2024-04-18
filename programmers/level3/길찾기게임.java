import java.util.*;

class Solution {
    Queue<Node> nodes = new PriorityQueue<>(Collections.reverseOrder());
    int[][] answer;
    int len, ai = 0;
    public int[][] solution(int[][] nodeinfo) {
        len = nodeinfo.length;
        answer = new int[2][len];
        
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes.add(new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]));
        }
        
        Node root = nodes.poll();
        while (!nodes.isEmpty()) {
            makeTree(root, nodes.poll());
        }
        
        preorder(root);
        ai = 0;
        postorder(root);
        
        return answer;
    }
    
    private void makeTree(Node parent, Node next) {
        if (next.x < parent.x) {
            if (parent.left == null) parent.left = next;
            else makeTree(parent.left, next);
            return;
        }
        
        if (parent.right == null) parent.right = next;
        else makeTree(parent.right, next);
    }
    
    private void preorder(Node curr) {
        answer[0][ai++] = curr.n;
        if (curr.left != null) preorder(curr.left);
        if (curr.right != null) preorder(curr.right);  
    }
    
    private void postorder(Node curr) {
        if (curr.left != null) postorder(curr.left);
        if (curr.right != null) postorder(curr.right);
        answer[1][ai++] = curr.n;
    }

    class Node implements Comparable<Node> {
        int n, x, y; 
        Node left, right;
        
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
