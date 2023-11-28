class Solution {
    public int solution(int n, int a, int b) {
        int currA = a, currB = b;
        int round = 1;

        while(currA != currB) {
            currA = currA % 2 == 1 ? (int) Math.ceil((currA+1)/2) : (int) Math.ceil(currA/2);
            currB = currB % 2 == 1 ? (int) Math.ceil((currB+1)/2) : (int) Math.ceil(currB/2);
            if (currA == currB) {
                return round; 
            }
            round++;
        }
        
        return 0;
    }
}
