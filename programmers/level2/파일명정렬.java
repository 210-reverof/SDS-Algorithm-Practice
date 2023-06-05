import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        List<File> fs = new ArrayList<>();
        
        for (String f : files) {
            fs.add(new File(f));
        }
        
        Collections.sort(fs);
        
        for (int i = 0; i < files.length; i++) {
            // System.out.print(fs.get(i).number + " ");
            answer[i] = fs.get(i).name;
        }
        
        return answer;
    }
    
    class File implements Comparable<File> {
        String name, head;
        int number = 0;
        
        public File(String name) {
            this.name = name;
            
            int numberStartIdx = name.replaceAll("[0-9]", "*").indexOf("*");
            this.head = name.substring(0, numberStartIdx).toUpperCase();
            
            String numStr = name.substring(numberStartIdx, 
                        Math.min(numberStartIdx + 5, name.length())).replaceAll("[^0-9]", "");
            this.number = Integer.parseInt(numStr);
        }
        
        @Override
        public String toString() {
            return this.name;
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
