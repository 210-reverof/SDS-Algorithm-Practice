class Solution {
   
    public String solution(int[] food) {
   
	String answer = "";
    String foodFront = "";
    String foodBack  = "";
	
    for(int i=1; i<food.length; i++) {
    	int f = (food[i]/2);
    	
    	for(int j=1; j<= f; j++) {
    	foodFront = foodFront + Integer.toString(i);
    	}
    }
    
    for(int i=food.length-1; i>=1; i--) {
    	int b = (food[i]/2);
    	
    	for(int j=b; j>=1; j--) {
        	foodBack = foodBack + Integer.toString(i);
        	}
    }
    answer = foodFront + "0" +foodBack;
        return answer;
    }
}
