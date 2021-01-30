import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE;
	static final int INF = Integer.MAX_VALUE;
	static final int MAX = 20000;
	static final int RED = 0, BLUE = 1;
	
	static int K,V,E;
	
	static ArrayList<Integer>[] graph = new ArrayList[MAX];
	static int[] visit = new int[MAX];
	static boolean interrupt;
	
	public static void main(String[] args) throws IOException {	
		K = io.inputInt();
		for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<Integer>();
		for (int i = 0; i < K; i++) {
			Init();
			if(Solve()) System.out.println("YES");
			else System.out.println("NO");
		}
	}
	
	static void Init() throws IOException{
		for (int i = 0; i < graph.length; i++) graph[i].clear();
		
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		V = nextInt(stk)-1;
		E = nextInt(stk);
		for (int i = 0; i < E; i++) {
			stk = new StringTokenizer(io.inputStr());
			Connect(nextInt(stk)-1, nextInt(stk)-1);
		}
		
	}
	
	static boolean Solve() throws IOException {
		interrupt = false;
		Arrays.fill(visit, -1);

		for (int i = 0; i <= V; i++) {
			if(visit[i] == -1) {
				visit[i] = RED;
				DFS(i);
			}
		}
		if(interrupt) return false;
		
		return true;
	}

	static void DFS(int here) {
		if(interrupt) return;
		for (int i = 0; i < graph[here].size(); i++) {
			int there = graph[here].get(i);
			if(visit[there] == -1) {
				visit[there] = (visit[here] == RED) ? BLUE:RED;
				DFS(there);
			}
			else if(visit[there] == visit[here]) {interrupt = true; return; }
		}
	}

	static void Connect(int a, int b) {
		graph[a].add(b);
		graph[b].add(a);
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
			System.out.print("[" + i + "] : ");
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