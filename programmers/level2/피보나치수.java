class Solution {
    int[] fibo;

    public int solution(int n) {
        fibo = new int[n+1];
        return dfs(n);
    }

    private int dfs(int n) {
        if(fibo[n] > 0) return fibo[n];
        if(n == 0) return 0;
        if(n == 1) return fibo[1] = 1;

        return fibo[n] = (dfs(n-1) + dfs(n-2))%1234567;
    }
}
