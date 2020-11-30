import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final long NINF = -10000000000l; 
	static final long INF = Long.MAX_VALUE/2l;
	static final long PATH_CHECK = 6000000000000l;
	
	static final int MAX_VERTEX = 501; 
	
	static int TOTAL,V,E;
	
	static ArrayList<Adjacent>[] GRAPH = new ArrayList[MAX_VERTEX];
	public static void main(String[] args) throws IOException{
		for (int i = 0; i < GRAPH.length; i++) GRAPH[i] = new ArrayList<Main.Adjacent>(); 
		
		Init();
		Solve();
	}
	static void Solve() {
		long[] ans = Bellman(0, GRAPH, V);
		StringBuilder sb = new StringBuilder();
		if(ans != null) {
			for (int i = 1; i < V; i++) {
				if(ans[i] < PATH_CHECK)	sb.append(String.valueOf(ans[i]));
				else sb.append(-1);
				sb.append('\n');
			}
			System.out.println(sb.toString());
		}
		else System.out.println(-1);
		
	}
	
	static void Init() throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		V = nextInt(stk);
		E = nextInt(stk);
		for (int i = 0; i < GRAPH.length; i++) GRAPH[i].clear();
		
		for (int i = 0; i < E; i++) {
			stk = new StringTokenizer(io.inputStr());
			Connect(nextInt(stk)-1,nextInt(stk)-1,nextInt(stk));
		}
	}
	
	static long[] Bellman(int start, ArrayList<Adjacent>[] graph,int graphSize) {
		long[] dist = new long[graphSize];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		boolean updated = false;
		int there;
		long thereCost;
		for (int loopCount = 0; loopCount < graphSize; loopCount++) {			
			updated = false;
			// 정점 순회
			for (int v = 0; v < graphSize; v++) {
				if(dist[v] == INF) continue;
				for (int i = 0; i < graph[v].size(); i++) {
					there = graph[v].get(i).a;
					thereCost = graph[v].get(i).w;
					if(dist[v]+thereCost < dist[there]) {
						dist[there] = dist[v]+thereCost;
						updated = true;
					}
				}
			}
			// 정점 순회 끝
			
			// V번 Loop 전, 최적 종료조건
			if(!updated) return dist;
		}
		// 마지막 바퀴에도 update가 발생했다. => 음수 사이클
		if(updated) return null;
		else return dist;
	}
	
	static void Connect(int a, int b, long w) {
		GRAPH[a].add(new Adjacent(b,w));
	}
	static class Adjacent {
		public int a;
		public long w;

		public Adjacent(int a, long b) {
			super();
			this.a = a;
			this.w = b;
		}
		
	}
	
	
	// ===================== functions for PS =====================
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
	static void Display(int[] arr, int limit) {
		//System.out.println("요소갯수 : " + arr.length);
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
			System.out.print("["+i+"] : ");
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}

// ************************************** //
// *-------------IO_Manager--------------* //
// ************************************** //
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