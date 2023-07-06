class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {0, 0};
        
        // 갈색 = (r + (c-2)) * 2
        // 노란색 = (r-2) * (c-2)
        int b = brown / 2;
        for (int r = 3; r < 2500; r++) {
            int num = r * (b + 2 - r) - 2 * (b + 2) + 4;
            if (num == yellow) {
                answer[0] = r;
                answer[1] = b + 2 - r;
            }
        }
        
        return answer;
    }
}
