import java.util.*;

class Solution {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    Set<Integer> vs = new HashSet<>();
    Queue<Integer> q = new LinkedList<>();
    Map<Integer, Character> labels = new HashMap<>();
    public int[] solution(int[][] edges) {        
        for (int[] edge: edges) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new LinkedList<>());
            }
            vs.add(edge[0]);
            vs.add(edge[1]);
            graph.get(edge[0]).add(edge[1]);
        }
        
        for (int v: vs) {
            if (isStartOfDonut(v)) {
                continue; 
            }  
            
            if (isStartOfStick(v)) {
                 continue; 
            }
            
            isStartOfEight(v);
        }
        
        
        for (int v: vs) {
            if (!labels.containsKey(v)) {
                int[] answer = new int[]{v, 0, 0, 0};
                for (int next: graph.get(v)) {
                    if (labels.get(next) == 'd') answer[1]++;
                    else if (labels.get(next) == 's') answer[2]++;
                    else answer[3]++;
                }
                return answer;
            }  
        }
        
        return new int[4];
    }
                 
    boolean isStartOfDonut(int v) {
        int next = v;
        while (true) {
            q.add(next);
            if (!graph.containsKey(next)) break;
            
            List<Integer> childs = graph.get(next);
            if (childs.size() != 1) break;
            
            next = childs.get(0);
            if (next == v) {
                addLabel('d');
                return true;
            }
        }
        
        q.clear();
        return false;
    }
    
    boolean isStartOfStick(int v) {
        int next = v;
        while (true) {
            q.add(next);
            if (!graph.containsKey(next)) {
                addLabel('s');
                return true;
            }
            
            List<Integer> childs = graph.get(next);
            if (childs.size() != 1 || childs.get(0) == next) break;
            
            next = childs.get(0);            
        }
        
        q.clear();
        return false;
    }
    
    boolean isStartOfEight(int v) {
        if (!graph.containsKey(v)) return false;
        
        List<Integer> firstChilds = graph.get(v);
        if (firstChilds.size() != 2) return false;
        
        q.add(v);
        boolean isEight = true;
        for (int c: firstChilds)  {
            int next = c;
            while (isEight) {
                q.add(next);
                
                if (!graph.containsKey(next)) {
                    isEight = false;
                    break;
                }
                
                List<Integer> childs = graph.get(next);
                if (childs.size() != 1 && childs.get(0) != v) {
                    isEight = false;
                    break;
                } 
                
                else if (childs.get(0) == v) {
                    break;
                }

                next = childs.get(0);
            }
        }
        
        if (isEight) addLabel('e');
        q.clear();
        return isEight;
    }
    
    private void addLabel(char num) {
        while (!q.isEmpty()) {
            labels.put(q.poll(), num);
        }
    }
}
