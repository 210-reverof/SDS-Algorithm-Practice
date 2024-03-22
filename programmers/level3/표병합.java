import java.util.*;

class Solution {
    int[] parent = new int[2500]; 
    Set<Integer>[] groups = new HashSet[2500];
    Map<Integer, String> values = new HashMap<>();
    public List<String> solution(String[] commands) {
        List<String> answer = new LinkedList<>();

        for (int i = 0; i < 2500; i++) {
            parent[i] = i;
            groups[i] = new HashSet<>();
        }

        for (String cmd : commands) {
            String[] val = cmd.split(" ");
            if (val[0].startsWith("UP")) {
                if (val.length == 4) {
                    update(getIdx(val[1], val[2]), val[3]);
                    continue;
                }

                updateVal(val[1], val[2]);
                continue;
            }

            if (val[0].startsWith("ME")) {
                merge(getIdx(val[1], val[2]), getIdx(val[3], val[4]));
                continue;
            }

            if (val[0].startsWith("UN")) {
                unmerge(getIdx(val[1], val[2]));
                continue;
            }

            answer.add(print(getIdx(val[1], val[2])));
        }

        return answer;
    }

    private void update(int idx, String val) {
        // 내 부모 찾고
        int parent = find(idx);
        
        // 부모의 텍스트 수정
        values.put(parent, val);
    }

    private void updateVal(String a, String b) {
        for (int idx : values.keySet()) {
            if (values.get(idx).equals(a)) {
                values.put(idx, b);
            }
        }
    }

    private void merge(int idx1, int idx2) {
        int p1 = find(idx1);
        int p2 = find(idx2);
        if (idx1 == idx2 || p1 == p2) return;
        
        // 더 작은 친구로 부모 정하고
        int parentIdx = Math.min(p1, p2);
        int childIdx = Math.max(p1, p2);
        Set<Integer> parentSet = groups[parentIdx];
        Set<Integer> childSet = groups[childIdx];
        parent[childIdx] = parentIdx;
        
        // 나랑, 내 자식들을 부모 세트에 삽입 후 내꺼에서 다 삭제
        parentSet.add(childIdx);
        parentSet.addAll(childSet);
        childSet.clear();
        
        // 대입해야 하는 값 얻고
        String nextVal = getNextVal(p1, p2);
        if (nextVal.equals("")) return;
        
        // 그 부모의 맵에 할당
        values.put(parentIdx, nextVal);
        if (values.containsKey(childIdx)) values.remove(childIdx);
    }

    private void unmerge(int idx) {
        // 지금 내 부모의 값을 tmp
        int pi = find(idx);
        String tmpVal = values.getOrDefault(pi, "");
            
        // 내 부모의 세트를 돌면서
        for (int child: groups[pi]) {
            
            // 자식들의 부모를 나 자신으로 만들고
            parent[child] = child;
        }
        
        // 부모 세트를 비운다
        groups[pi].clear();
        
        // 부모값 없애고 나의 값을 tmp로 설정
        if (tmpVal.equals("")) return;
        values.remove(pi);
        values.put(idx, tmpVal);
    }

    private String print(int idx) {
        // 내 부모를 찾아서
        int pi = find(idx);
        
        // 부모 값으로 공백이든 값이든 반환
        return values.containsKey(pi)? values.get(pi) : "EMPTY";
    }

    private int find(int n) {
        if (n == parent[n]) return n;
        return parent[n] = find(parent[n]);
    }
                       
    private int getIdx(String r, String c) {
        return (Integer.parseInt(r)-1) * 50 + (Integer.parseInt(c)-1);
    }
                       
    private String getNextVal(int a, int b) {
        // 만약 둘 다 없으면 ""
        if (!values.containsKey(a) && !values.containsKey(b)) return "";
        
        // a만 있으면 a
        if (values.containsKey(a)) return values.get(a);
        
        // b
        return values.get(b);
    }
}
