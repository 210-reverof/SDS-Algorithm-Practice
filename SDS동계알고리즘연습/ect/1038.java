import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static int N;
	public static List<Long> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());

		if (N > 1022) {
			System.out.println(-1);
			System.exit(0);
		}
		
		list = new ArrayList<Long>();
		for (int i = 0; i < 10; ++i) {

			calc(i, 1);
		}
		

		Collections.sort(list);
		System.out.println(list.get(N));
	}
	
	public static void calc(long num, int len) {

		if (len > 10) {
			return;
		}
		
		list.add(num);
		
		for (int i = 0; i < 10; ++i) {

			if (num % 10 > i) {
				calc((num * 10) + i, len + 1);
			}
		}
	}
}
