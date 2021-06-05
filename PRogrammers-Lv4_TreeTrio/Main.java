import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 2;
	static final int INF = Integer.MAX_VALUE / 2;
	static final int MAX = 1000;
	public Solution solve = new Solution();

	public static void main(String[] args) throws IOException {
		Execute();
	}
	static void Execute() {
		Main mainObj = new Main();
		int ans = mainObj.solve.solution(3,new int[][] {{1,2},{2,3}});
		System.out.println(ans);
		// Display(ans, ans.length);
	}

	// import java.util.*;
	class Solution {
		ArrayList<Integer>[] graph;
		int maxDepth,vertex,vertexCount;
		
		private int next;
		
		public int solution(int n, int[][] edges) {
			graph = new ArrayList[n+1];
			for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<Integer>();	
			for (int i = 0; i < edges.length; i++) {
				int a = edges[i][0], b = edges[i][1];
				graph[a].add(b); graph[b].add(a);
			}
			

			//가장 깊은 곳을 찾는다.
			int v1 = Get_Deepest(1);
			//가장 깊은 곳에서 가장 먼곳을 찾는다.
			int v2 = Get_Deepest(v1);
			
			if (vertexCount > 1) return maxDepth;
			else {
				Get_Deepest(v2);
				if (vertexCount > 1) return maxDepth;
				else return maxDepth-1;
			}
		}
		public int Get_Deepest(int start) {
			maxDepth = 0;
			vertex = -1;
			vertexCount = 0;
			DFS(-1,start,0);
			return vertex;
		}
		
		public void DFS(int previous,int here,int depth) {
			if(maxDepth < depth) {
				vertexCount = 1;
				maxDepth = depth;
				vertex = here;
			} 
			else if(maxDepth == depth) vertexCount++;
			
			for (int i = 0; i < graph[here].size(); i++) {
				next = graph[here].get(i);
				if(next != previous) DFS(here,next,depth+1);
			}
		}
	}

//	===================== ETC functions for PS =====================
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	static long Min(long a, long b) {
		return (a > b) ? b : a;
	}
	static long Max(long a, long b) {
		return (a > b) ? a : b;
	}
	static int Min(int a, int b) {
		return (a > b) ? b : a;
	}
	static int Max(int a, int b) {
		return (a > b) ? a : b;
	}
	static double Min(double a, double b) {
		return (a > b) ? b : a;
	}
	static double Max(double a, double b) {
		return (a > b) ? a : b;
	}
	static void Display(int[] arr, int limit) {
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		for (int i = 0; i < limit; i++) {
			for (int j = 0; j < limit; j++) {
				System.out.printf(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}

//-------------IO_Manager--------------
class IO_Manager {
	public BufferedReader br;
	public BufferedWriter bw;

	public IO_Manager() {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
	}
	public Integer inputInt() throws IOException {
		return Integer.parseInt(br.readLine());
	}
	public Long inputLong() throws IOException {
		return Long.parseLong(br.readLine());
	}
	public String inputStr() throws IOException {
		return br.readLine();
	}
	public void write(String str) throws IOException {
		bw.write(str);
	}
	public void close() throws IOException {
		br.close();
		bw.close();
	}
}