import java.util.*;
import java.io.*;
import java.util.stream.*;
 
import static java.util.Arrays.*;
 
public class Main {
 
    static int index,sccCount;
    static int[] discover,finish,sccNum,inDegree;
    static ArrayList<ArrayList<Integer>> graph;
    static Stack<Integer> st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        while (tc-->0){
            graph = new ArrayList<>();
            int[] input = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            for(int i=0;i<input[0];i++) graph.add(new ArrayList<>());
            for(int i=0;i<input[1];i++){
                int[] edge = stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();
                graph.get(edge[0]).add(edge[1]);
            }
            findScc();
            setIndegree();
            String ans = getStartPoint();
            System.out.println(ans);
            br.readLine();
        }
    }
 
    static void findScc(){
 
        st = new Stack<>();
        index=0; sccCount=0;
        finish = new int[graph.size()];
        discover = new int[graph.size()];
        sccNum = new int[graph.size()];
 
        for(int i=0;i<graph.size();i++)
            if(discover[i]==0) dfs(i);
    }
 
    private static void setIndegree() {
        inDegree = new int[sccCount+1];
        for(int i=0;i<graph.size();i++) {
            for (Integer next : graph.get(i))
                if (sccNum[next] != sccNum[i]) inDegree[sccNum[next]]++;
        }
    }
 
    private static String getStartPoint() {
        int zeroCount=0;
        ArrayList<Integer> sccList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<inDegree.length;i++){
            if(inDegree[i]==0 && zeroCount==0){
                zeroCount++;
                for(int j=0;j<sccNum.length;j++)
                    if(i==sccNum[j]) sccList.add(j);
            }else if(inDegree[i]==0 && zeroCount!=0){
                sb.append("Confused").append("\n");
                return sb.toString();
            }
        }
        Collections.sort(sccList);
        sccList.forEach(e->sb.append(e).append("\n"));
        return sb.toString();
    }
 
    private static int dfs(int cur) {
 
        discover[cur] = ++index;
        st.push(cur);
 
        int parent = discover[cur];
 
        for (Integer next : graph.get(cur)) {
 
            if(discover[next]==0) parent = Math.min(parent,dfs(next));
            else if(finish[next]==0) parent = Math.min(parent,discover[next]);
        }
        if(parent == discover[cur]){
            while (true){
                Integer pop = st.pop();
                finish[pop]=1;
                sccNum[pop] = sccCount+1;
                if(pop==cur) break;
            }sccCount++;
        }
        return parent;
    }
}
