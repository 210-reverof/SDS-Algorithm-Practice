import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int answerRuntime = -1;
        
        char[] my = getNotes(m);
        int myLen = my.length;
        for (String info: musicinfos) {
            String[] infos = info.split(",");
            int runtime = getRuntime(infos[0], infos[1]);
            char[] curr = getNotes(infos[3]);
            int len = curr.length;
            
            for (int i = 0; i < runtime; i++) {
                boolean isSame = true;
                for (int j = 0; j < myLen; j++) {
                    if (curr[(i+j)%len] != my[j]) {
                        isSame = false;
                        break;
                    }
                }
                
                if (isSame && answerRuntime < runtime) {
                    answer = infos[2];
                    answerRuntime = runtime;
                }
            }
        }
        
        return answer;
    }
    
    private int getRuntime(String start, String end) {
        return getTime(end) - getTime(start);
    }
    
    private int getTime(String str) {
        return Integer.parseInt(str.substring(0,2)) * 60 + Integer.parseInt(str.substring(3,5));
    }
    
    private char[] getNotes(String str) {
        int resultLength = str.replaceAll("#", "").length();
        char[] notes = new char[resultLength];
        
        char[] letters = str.toCharArray();
        int size = letters.length, resultIdx = 0;
        for (int i = 0; i < size; i++) {
            if (i < size - 1 && letters[i+1] == '#') {
                notes[resultIdx++] = (char)(letters[i++] + 10);   
                continue;
            }
            notes[resultIdx++] = letters[i];
        }
        
        return notes;
    }
}
