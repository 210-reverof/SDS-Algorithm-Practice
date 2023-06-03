import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int size = elements.length;
        int[] nums = new int[size * 2];
        int[] sums = new int[size * 2];
        System.arraycopy(elements, 0, nums, 0, size);
        System.arraycopy(elements, 0, nums, size, size);
        
        Set<Integer> results = new HashSet<>();
        
        int sum = 0;
        for (int i = 0; i < size * 2; i++) {
            sum += nums[i];
            sums[i] = sum;
        }
        
        for (int i = 1; i <= size; i++) {
            for (int j = 0; j < size; j++) {
                results.add(sums[j+i] - sums[j]);
            }
        }
        
        return results.size();
    }
}
