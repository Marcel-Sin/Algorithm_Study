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
	static final int NINF = Integer.MIN_VALUE;
	static final int INF = Integer.MAX_VALUE/2;
	static final int MAX = 100001;
	
	static int N,longest_Vertex,longest_Length;
	
	static ArrayList<Pair>[] graph = new ArrayList[MAX];
	static boolean[] visited = new boolean[MAX];
	
	public static void main(String[] args) throws IOException {	
		Init();
		System.out.println(Solve());
	}
	static void Init() throws IOException{
		for (int i = 0; i < graph.length; i++) { graph[i] = new ArrayList<Pair>(10); }
		N = io.inputInt();
		
		//Input Format : 4 [2 4] [3 3] [5 6] -1
		int curVertex,to,weight;
		for (int i = 0; i < N; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			curVertex = nextInt(stk);
			while((to = nextInt(stk)) != -1) {
				Connect(curVertex,to,nextInt(stk));
			}
		}
		
		
	}
	
	static int Solve() {
		
		//첫번째 가장 깊숙한 곳 찾기
		longest_Length = -1;
		longest_Vertex = 1;
		Arrays.fill(visited, false);
		DFS(1,0); 
		
		//가장 깊숙한 곳에서 다른 깊숙한 곳 찾아보기
		longest_Length = -1;
		Arrays.fill(visited, false);
		DFS(longest_Vertex,0); 
		
		return longest_Length;
	}
	
	static void DFS(int here,int curLen) {
		visited[here] = true;
		if(curLen > longest_Length) {
			longest_Vertex = here;
			longest_Length = curLen;
		}
		for (int i = 0; i < graph[here].size(); i++) {
			Pair pair = graph[here].get(i);
			int there = pair.v;
			if(visited[there] == false) DFS(there,curLen+pair.cost);
		}
	}

	static void Connect(int a,int b,int w) {
		graph[a].add(new Pair(b,w));
		graph[b].add(new Pair(a,w));
	}
		
	static class Pair {
		public int v,cost;

		public Pair(int v, int cost) {
			super();
			this.v = v;
			this.cost = cost;
		}

		
	}
	
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ===================== functions for PS =====================
	// ============================================================
	// ============================================================
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
		// System.out.println("요소갯수 : " + arr.length);
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
			for (int j = 0; j < limit; j++) {
				System.out.printf("%2d ",arr[i][j]);
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