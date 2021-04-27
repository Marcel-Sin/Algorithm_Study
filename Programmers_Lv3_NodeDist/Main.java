import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
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
		long ans = mainObj.solve.solution(6,new int[][] {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}});
		//Display(ans, ans.length);
		System.out.println(ans);
	}
	
	// import java.util.*;
	class Solution {
		ArrayList<Integer>[] link;
		int[] visited;
	   public long solution(int n, int[][] edge) {
	      link = new ArrayList[n];
	   	visited = new int[n];
	   	Arrays.fill(visited, -1);
	      for (int i = 0; i < n; i++) link[i] = new ArrayList<Integer>();
	   	for (int i = 0; i < edge.length; i++) {
	   		int a=edge[i][0]-1,b=edge[i][1]-1;
				link[a].add(b);
				link[b].add(a);
			}
	   	BFS();
	   	int ret = 0,max=0;
	      for (int i = 0; i < n; i++) {
	      	max = (max < visited[i]) ? visited[i]:max;
			}
	      for (int i = 0; i < n; i++) {
	      	if(visited[i]==max) ret++;
			}
	   	return ret;
	   }
	   void BFS() {
	   	Queue<Integer> queue = new LinkedList<Integer>();
	   	queue.add(0);
	   	visited[0] = 0;
	   	while(queue.isEmpty() == false) {
	   		int here = queue.poll();
	   		for (int i = 0; i < link[here].size(); i++) {
					int there = link[here].get(i);
					if(visited[there] == -1) {
						visited[there] = visited[here]+1;
						queue.add(there);
					}
				}
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
				System.out.printf(arr[i][j]+" ");
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