import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = -100000000; 
	static final int INF = 100000000;
	static final int MAX_VERTEX = 1001; 
	
	static int TOTAL,V,E;
	static int[][] matrix = new int[MAX_VERTEX][MAX_VERTEX];
	static ArrayList<Integer> fire = new ArrayList<Integer>();
	static ArrayList<Integer> fighter = new ArrayList<Integer>();
	
	static boolean[] visited = new boolean[MAX_VERTEX];
	static int[] dist = new int[MAX_VERTEX];
	
	
	public static void main(String[] args) throws IOException{
		
		TOTAL = io.inputInt();
		for (int i = 0; i < TOTAL; i++) {
			Init();
			System.out.println(Solve());
		}
		
		
		
	}
	
	
	static int Solve() {
		//StartIDX : 0 , pre-processing
		for(int i = 0; i < fighter.size(); i++) matrix[0][fighter.get(i)] = 0;
		dist[0] = 0;
		
		while(true) {
			int here = NotVisit_Smallest_Index(dist, V);
			if(here == -1) break;
			
			visited[here] = true;
			for (int there = 0; there <= V; there++) {
				if(matrix[here][there] != -1 && matrix[here][there]+dist[here] < dist[there]) {
					dist[there] = matrix[here][there]+dist[here];
				}
			}
		}
		
		int ret = 0;
		for(int i = 0; i < fire.size(); i++) {
			ret += dist[fire.get(i)];
		}
		
		return ret;
	}
	
	static int NotVisit_Smallest_Index(int[] arr,int included_Length) {
		int ret = -1, value = INF;
		for (int i = 0; i <= included_Length; i++) {
			if(arr[i] < value && !visited[i]) {
				ret = i;
				value = arr[i];
			}
		}
		return ret;
	}
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		V = nextInt(stk);
		E = nextInt(stk);
		// n, m 버림
		
		// Clear
		fire.clear();
		fighter.clear();
		for(int i = 0; i <= V; i++) for (int j = 0; j <= V; j++) matrix[i][j] = -1;
		for(int i = 0; i <= V; i++) dist[i] = INF;
		for(int i = 0; i <= V; i++) visited[i] = false;
		
		for (int i = 0; i < E; i++) {
			stk = new StringTokenizer(io.inputStr());
			Connect(nextInt(stk),nextInt(stk),nextInt(stk));
		}
		
		stk = new StringTokenizer(io.inputStr());
		while(stk.hasMoreTokens()) fire.add(nextInt(stk));
		
		stk = new StringTokenizer(io.inputStr());
		while(stk.hasMoreTokens()) fighter.add(nextInt(stk));

	}
	static void Connect(int a, int b,int w) {
		matrix[a][b] = w;
		matrix[b][a] = w;
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