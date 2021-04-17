import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
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
		int ans = mainObj.solve.solution(4,new int[][] {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}});
		//Display(ans, ans.length);
		System.out.println(ans);
	}
	
	//import java.util.*;
	class Solution {
		ArrayList<Pair>[] bridge;
		boolean[] isConnected;
		
		public int solution(int n, int[][] costs) {
			//크기에 따른 초기화
			bridge = new ArrayList[n];
			for (int i = 0; i < bridge.length; i++) bridge[i] = new ArrayList<Pair>();
			isConnected = new boolean[n];
			
			// 각 섬에서 건설가능 다리 입력
			for (int i = 0; i < costs.length; i++) Connect(costs[i][0],costs[i][1] ,costs[i][2]);
			
			// 시작점의 건설 가능 다리들을 큐로 넣어주자
			PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
			int startIdx = costs[0][0];
			isConnected[startIdx] = true;
			for (int i = 0; i < bridge[startIdx].size(); i++) pq.add(bridge[startIdx].get(i));
			
			int total_Cost = 0;
			while(!pq.isEmpty()) {
				Pair pair = pq.poll();
				if(isConnected[pair.key] == true) continue;
				else {
					total_Cost += pair.value;
					isConnected[pair.key] = true;
					for (int i = 0; i < bridge[pair.key].size(); i++) pq.add(bridge[pair.key].get(i));
				}
			}
			return total_Cost;
		}
		void Connect(int from,int to, int cost) {
			bridge[from].add(new Pair(to,cost));
			bridge[to].add(new Pair(from,cost));
		}
		class Pair implements Comparable<Pair>{
			int key,value;

			public Pair(int key, int value) {
				super();
				this.key = key;
				this.value = value;
			}

			@Override
			public int compareTo(Pair o) {
				if(this.value == o.value) return 0;
				else return (this.value < o.value) ? -1:1;
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