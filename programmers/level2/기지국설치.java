class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0, sIdx = 0, i = 1;

        while (i <= n) {
            if (sIdx < stations.length && i >= stations[sIdx] - w) {
                i = stations[sIdx] + w + 1;
                sIdx++;
                continue;
            }
            
            i += 2 * w + 1;
            answer++;
        }

        return answer;
    }
}
