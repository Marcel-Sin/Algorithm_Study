import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
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
		int ans = mainObj.solve.solution("hit","cog",new String[] {"hot", "dot", "dog", "lot", "log", "cog"});
		//Display(ans, ans.length);
		System.out.println(ans);
	}
	
	//import java.util.*;
	class Solution {
		int N;
		HashMap<String, Integer> toIdx = new HashMap<String, Integer>();
		ArrayList<Integer>[] graph;
		
		public int solution(String begin, String target, String[] words) {
			N = words.length+1;
			graph = new ArrayList[N];
			for (int i = 0; i < N; i++) graph[i] = new ArrayList<Integer>();	
			
			// 탐색 시작 지점[0] 연결점 형성
			for (int i = 0; i < words.length; i++) {
				if(LetterDifCount(begin, words[i]) == 1) graph[0].add(i+1);
			}
			
			// 그외 모든 연결점 형성
			int start = 0,dest = -1;
			for (int i = 0; i < N-1; i++) {
				String a = words[i];
				toIdx.put(a, i+1);
				if(a.compareTo(target) == 0) dest = i+1;
				for (int j = 0; j < N-1; j++) {
					if(i == j) continue;
					String b = words[j];
					if(LetterDifCount(a, b) == 1) graph[i+1].add(j+1);
				}
			}
			if(dest == -1) return 0;
			else return BFS(start,dest);
			
		}
		int LetterDifCount(String a, String b) {
			int ret = 0;
			int len = a.length();
			for(int i = 0; i < len; i++) {
				if(a.charAt(i) != b.charAt(i)) ret++;
			}
			return ret;
		}
		
		int BFS(int start,int dest) {
			boolean[] visited = new boolean[N];
			Queue<Pair> queue = new LinkedList<Pair>();
			visited[start] = true;
			queue.add(new Pair(start,0));
			
			while(!queue.isEmpty()) {
				Pair here = queue.poll();
				if(here.v == dest) return here.count;
				for (int i = 0; i < graph[here.v].size(); i++) {
					int there = graph[here.v].get(i);
					if(visited[there] == false) {
						visited[there] = true;
						queue.add(new Pair(there,here.count+1));
					}
				}
			}
			return 0;
		}
		class Pair {
			public int v,count;

			public Pair(int v, int count) {
				super();
				this.v = v;
				this.count = count;
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