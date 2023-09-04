class Solution {
    static int[] answer;
    public int[] solution(int[][] arr) {
        answer = new int[2];

        recursion(arr, 0, 0, arr.length);
        return answer;
    }
    
    void recursion(int[][] arr, int x, int y, int size) {
        if (compact(arr, x, y, size)) {
            answer[arr[x][y]]++;
            return;
        }
        
        recursion(arr, x, y, size / 2);
        recursion(arr, x + size / 2, y, size / 2);
        recursion(arr, x, y + size / 2, size / 2);
        recursion(arr, x + size / 2, y + size / 2, size / 2);
    }

    static boolean compact(int[][] arr, int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                // 영역의 첫번째 값과 나머지를 비교하다 하나다로 다르면 반환
                if(arr[x][y] != arr[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
