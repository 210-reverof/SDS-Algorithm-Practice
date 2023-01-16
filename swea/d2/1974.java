import java.util.*;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
        sc.nextLine();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            List<Set<Integer>> verticalSets = new ArrayList<>();
            List<Set<Integer>> areaSets = new ArrayList<>();
            
            for (int i = 0;i < 9; i++) {
               verticalSets.add(new HashSet<>());
               areaSets.add(new HashSet<>());
            }
            
            boolean isTrue = true;
            for (int i = 0;i < 9; i++) {
                Set<Integer> currentSet = new HashSet<>();
                String str = sc.nextLine();
                String[] nums = str.split(" ");

                for (int j = 0;j < 9; j++) {
                    int num = Integer.parseInt(nums[j]);
                    currentSet.add(num);
                    verticalSets.get(j).add(num);
                    areaSets.get(getArea(i, j)).add(num);
            	}
                
                if (currentSet.size() != 9) {
                	isTrue = false;
                    continue;
                }
            }
            
            if (!isTrue) {
                System.out.println("#" + test_case + " 0");
                continue;
            }
            
            for (int i = 0;i < 9;i++) {
                if (verticalSets.get(i).size() != 9) isTrue = false;
                if (areaSets.get(i).size() != 9) isTrue = false;
            }
            
            if (!isTrue) {
                System.out.println("#" + test_case + " 0");
                continue;
            }
            
            System.out.println("#" + test_case + " 1");
		}
    }
    
    private static int getArea(int i, int j) {
        if (i<3 && j<3) return 0;
        if (i<3 && j<6) return 1;
        if (i<3) return 2;
        if (i<6 && j < 3) return 3;
        if (i<6 && j < 6) return 4;
        if (i<6) return 5;
        if (i<9 && j < 3) return 6;
        if (i<9 && j < 6) return 7;
        else return 8;
    }
}
