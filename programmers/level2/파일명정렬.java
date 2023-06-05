import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        List<File> fs = new ArrayList<>();
        
        for (String f : files)
            fs.add(new File(f));
        
        Collections.sort(fs);
        
        for (int i = 0; i < files.length; i++)
            answer[i] = fs.get(i).name;
        
        return answer;
    }
    
    class File implements Comparable<File> {
        String name, head;
        int number = 0;
        
        public File(String name) {
            this.name = name;
            this.head = name.split("[0-9]")[0].toUpperCase();
            String numStr = name.substring(this.head.length(), Math.min(this.head.length() + 5, name.length()));
            numStr = numStr.split("[^0-9]")[0];
            this.number = Integer.parseInt(numStr);
            
        }
        
        @Override
        public int compareTo(File f) {
            int headDiff = this.head.compareTo(f.head);
            if (headDiff != 0) return headDiff;
            
            int numDiff = this.number - f.number;
            return numDiff;
        }
        
    }
}
