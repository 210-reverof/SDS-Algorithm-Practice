import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

// 저울 문제
// 그리디 알고리즘
public class p2437 {
	static ArrayList<Integer> list;
	static HashSet<Integer> lists = new HashSet<Integer>();
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] elements = new int[n];
		for(int i = 0; i < n; i++) {
			int ele = Integer.valueOf(st.nextToken());
			elements[i] = ele;
		}
		
		Arrays.sort(elements);
		
	
		int max = 0;
		for(int i = 0; i < n; i++) {
			if(max + 1 >= elements[i]) {
				max += elements[i];
			}
			else {
				break;
			}
		}
		int result = max + 1;
		System.out.println(result);
	}
}
