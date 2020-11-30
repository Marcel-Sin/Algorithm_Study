import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = -1000000000; 
	static final int INF = 1000000000;
	static final int PATH_CHECK = 100000000;
	
	static final int MAX_VERTEX = 100; 
	
	static int TOTAL,V,E;
	
	static ArrayList<Adjacent>[] GRAPH = new ArrayList[MAX_VERTEX];
	static int[] minDist = new int[MAX_VERTEX];
	static int[] maxDist = new int[MAX_VERTEX];
	static boolean[][] path = new boolean[MAX_VERTEX][MAX_VERTEX];
	
	public static void main(String[] args) throws IOException{
		for (int i = 0; i < GRAPH.length; i++) GRAPH[i] = new ArrayList<Main.Adjacent>(); 
		TOTAL = io.inputInt();
		for (int i = 0; i < TOTAL; i++) {
			Init();
			Solve();
		}
	}

	
	static void Init() throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		V = nextInt(stk);
		E = nextInt(stk);
		for (int i = 0; i < GRAPH.length; i++) {
			GRAPH[i].clear();
			Arrays.fill(path[i], false);
		}
		
		
		for (int i = 0; i < E; i++) {
			stk = new StringTokenizer(io.inputStr());
			Connect(nextInt(stk),nextInt(stk),nextInt(stk));
		}
		
		
	}
	
	static void Solve() {
		boolean[] check = Bellman(0,1,GRAPH,V);
		if(check[0] == true && minDist[1] == INF) {
			System.out.println("UNREACHABLE");
			return;
		}
		
		if(check[0] == true && minDist[1] != INF ) System.out.printf("%d ",minDist[1]);
		else System.out.print("INFINITY ");
		
		if(check[1] == true && maxDist[1] != INF ) System.out.printf("%d ",maxDist[1]);
		else System.out.print("INFINITY ");
		System.out.println();
	}
	
	static boolean[] Bellman(int start, int dest,ArrayList<Adjacent>[] graph, int graphSize) {
		boolean[] ret = new boolean[2];
		
		Arrays.fill(minDist, INF);
		Arrays.fill(maxDist, NINF);
		
		minDist[start] = 0;
		maxDist[start] = 0;
		
		boolean min_Updated = false , max_Updated = false;
		int there, thereCost;
		
		for (int iter = 0; iter < graphSize; iter++) {
			min_Updated = false;
			max_Updated = false;
			for (int v = 0; v < graphSize; v++) {
				for (int i = 0; i < graph[v].size(); i++) {
					there = graph[v].get(i).a;
					thereCost = graph[v].get(i).b;
					if(ret[0] == false && minDist[v] != INF && minDist[v] + thereCost < minDist[there]) {
						minDist[there] = minDist[v] + thereCost;
						min_Updated = true;
					}
					if(ret[1] == false && maxDist[v] != NINF && maxDist[v] + thereCost > maxDist[there]) {
						maxDist[there] = maxDist[v] + thereCost;
						max_Updated = true;
					}
				}
			}
			if(!min_Updated) ret[0] = true;
			if(!max_Updated) ret[1] = true;
		}
		// 추가로 업데이트가 발생하려하면 정점 v는 1로 가는 중 무한 사이클에 포함되어 있다.
		
		min_Updated = false;
		max_Updated = false;
		for (int v = 0; v < graphSize; v++) {
			for (int i = 0; i < graph[v].size(); i++) {
				there = graph[v].get(i).a;
				thereCost = graph[v].get(i).b;
				if(minDist[v] != INF && minDist[v] + thereCost < minDist[there]) {
					if(path[start][v] && path[v][dest]) min_Updated = true;
				}
				if(maxDist[v] != NINF && maxDist[v] + thereCost > maxDist[there]) {
					if(path[start][v] && path[v][dest]) max_Updated = true;
				}
			}
		}
		
		if(min_Updated == true) ret[0] = false;
		else ret[0] = true;
		if(max_Updated == true) ret[1] = false;
		else ret[1] = true;
		
		return ret;
	}
	
	static void Connect(int a, int b, int w) {
		GRAPH[a].add(new Adjacent(b,w));
		path[a][b] = true;
		for (int i = 0; i < V; i++) if(path[i][a] == true) path[i][b] = true;  
		
	}
	static class Adjacent {
		public int a,b;

		public Adjacent(int a, int b) {
			super();
			this.a = a;
			this.b = b;
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