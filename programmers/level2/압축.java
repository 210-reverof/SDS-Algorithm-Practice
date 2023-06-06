import java.util.*;

class Solution {
    public List<Integer> solution(String msg) {
        List<Integer> ans = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        int idx = 27;
        
        for (int i = 0; i < 26; i++)
            map.put((char)('A' + i) + "", i+1);
        
        int start = 0, end = 2;
        while(end <= msg.length()) {
            String curr = msg.substring(start, end);
            
            if (map.containsKey(curr)) {
                end++;
                continue;
            }
            
            ans.add(map.get(msg.substring(start, end-1)));
            map.put(curr, idx++);
            start += (end - start - 1);
            end++;
        }
        
        ans.add(map.get(msg.substring(start, end-1)));
        
        return ans;
    }
}
