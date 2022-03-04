import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static star[] stars;
	static double answer;

	static StringBuilder sb = new StringBuilder();

	public static int parseInt(String string) {
		return Integer.parseInt(string);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		n = parseInt(br.readLine());
		stars = new star[n];
		
		for(int i = 0; i < n ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			
			stars[i] = new star(x, y);
		}
		
		PriorityQueue<line> pq = new PriorityQueue<>();
		for(int i = 0 ; i < n ; i++) {
			for(int j = i + 1 ; j < n ; j++) {				
				pq.add(new line(i, j, calculator(stars[i], stars[j])));
			}
		}
		
		answer = 0;
		Kruskal(pq);
		
		System.out.println(String.format("%.2f", answer));
	}
	
	static void Kruskal(PriorityQueue<line> pq) {
		
		int[] parent = new int[n];
		for(int i = 0 ; i < n ; i++) {
			parent[i] = i;
		}
		
		int count = 0;
		while(!pq.isEmpty()) {			
			line top = pq.poll();
			
			if(union(parent, top.u, top.v)) {
				answer += top.dist;
				
				if(++count == n - 1) {
					return;
				}
			}
		}
	}
	
	static int find(int[] parent, int v) {
		
		if(parent[v] == v) {
			return v;
		}
		
		return parent[v] = find(parent, parent[v]);		
	}
	
	static boolean union(int[] parent, int a, int b) {
		a = find(parent, a);
		b = find(parent, b);
		
		if(a == b) {
			return false;
		}
		
		if(a > b) {
			parent[a] = b;
		}
		else {
			parent[b] = a;
		}
		
		return true;
	}
	
	static double calculator(star a, star b) {
		return Math.sqrt(Math.pow(Math.abs(a.x - b.x), 2) + Math.pow(Math.abs(a.y - b.y), 2));
	}
	
	static class line implements Comparable<line>{
		int u, v;
		double dist;
		
		line(int u, int v, double dist){
			this.u = u;
			this.v = v;
			this.dist = dist;
		}

		@Override
		public int compareTo(line l) {
			return Double.compare(this.dist, l.dist);
		}
	}
	
	static class star{
		double x, y;
		star(double x, double y){
			this.x = x;
			this.y = y;
		}
	}
}
