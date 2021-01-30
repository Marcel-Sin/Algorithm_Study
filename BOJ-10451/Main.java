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
	static final int MAX = 1001;
	
	static int TEST,N;
	
	static ArrayList<Integer>[] graph = new ArrayList[MAX];
	static int[] visit = new int[MAX];
	
	public static void main(String[] args) throws IOException {	
		TEST = io.inputInt();
		for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<Integer>();
		for (int i = 0; i < TEST; i++) {
			Init();
			System.out.println(Solve());
		}
	}
	
	static void Init() throws IOException{
		for (int i = 0; i < graph.length; i++) graph[i].clear();
		N = io.inputInt();
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		for (int i = 1; i <= N; i++) {
			int b = nextInt(stk);
			Connect(i, b);
		}
		
	}
	
	static int Solve() throws IOException {
		int ret = 0;
		Arrays.fill(visit, 0);

		for (int i = 1; i <= N; i++) {
			if(visit[i] == 0) {DFS(i); ret++;}
		}
		return ret;
	}

	static void DFS(int here) {
		visit[here] = 1;
		if(visit[graph[here].get(0)] == 0) DFS(graph[here].get(0));
	}

	static void Connect(int a, int b) {
		graph[a].add(b);
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