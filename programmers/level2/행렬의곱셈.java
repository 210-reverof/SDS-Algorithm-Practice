class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int a = arr1.length, b = arr2[0].length;
        int length = arr1[0].length;
        int[][] answer = new int[a][b];
        
        for (int i = 0; i < a; i++)
            for (int j = 0; j < b; j++) 
                for (int k = 0; k < length; k++)
                    answer[i][j] += (arr1[i][k] * arr2[k][j]);
        
        return answer;
    }
}
